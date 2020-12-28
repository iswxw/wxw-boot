### Memcached 缓存技术



Memcached 是一个高性能的分布式内存对象缓存系统，用于动态Web应用以减轻数据库负载。它通过在内存中缓存数据和对象来减少读取数据库的次数，从而提高动态、数据库驱动网站的速度。Memcached基于一个存储键/值对的hashmap。其守护进程（daemon ）是用C写的，但是客户端可以用任何语言来编写，并通过memcached协议与守护进程通信。

为了提高性能，memcached中保存的数据都存储在memcached内置的内存存储空间中。由于数据仅存在于内存中，因此重启memcached、重启操作系统会导致全部数据消失。另外，内容容量达到指定值之后，就基于LRU(Least Recently Used)算法自动删除不使用的缓存。memcached本身是为缓存而设计的服务器，因此并没有过多考虑数据的永久性问题。



### 基础知识

#### 1. Memcached 安装

> **memcached >= 1.4.5 版本安装** 

1. 解压下载的安装包到指定目录。

2. 在 memcached1.4.5 版本之后，memcached 不能作为服务来运行，需要使用任务计划中来开启一个普通的进程，在 window 启动时设置 memcached自动执行。

我们使用管理员身份执行以下命令将 memcached 添加来任务计划表中：

```
schtasks /create /sc onstart /tn memcached /tr "'c:\memcached\memcached.exe' -m 512"
```

**注意：**你需要使用真实的路径替代 c:\memcached\memcached.exe。

**注意：****-m 512** 意思是设置 memcached 最大的缓存配置为512M。

**注意：**我们可以通过使用 "*c:\memcached\memcached.exe -h*" 命令查看更多的参数配置。

3. 如果需要删除 memcached 的任务计划可以执行以下命令：

```
schtasks /delete /tn memcached
```

相关文章

- [memcached 安装](https://www.runoob.com/memcached/window-install-memcached.html) 

#### 2. Java 客户端 XMemcached

使用最为广泛的Memcache Java客户端，是一个全新的 Java Memcache Client。Memcache 通过它自定义协议与客户端交互，而XMemcached就是它的Java客户端实现，相比较于其他客户端来说XMemcached 的优点如下：

**XMemcached主要特性** 

XMemcached 支持设置连接池、宕机报警、使用二进制文件、一致性Hash、进行数据压缩等操作，总结下来有以下一些点

- 性能优势，使用的NIO
- 协议支持广泛
- 支持客户端分布，提供了一致性Hash 实现
- 允许设置节点权重，XMemcached允许通过设置节点的权重来调节Memcached的负载，设置的权重越高，该Memcached节点存储的数据就越多，所要承受的负载越大
- 动态的增删节点，Memcached允许通过JMX或者代码编程来实现节点的动态的添加或者删除操作。方便扩展或者替换节点。
- XMemcached 通过 JMX 暴露的一些接口，支持Client本身的监控和调整，允许动态设置调优参数、查看统计数据、动态增删节点等；
- 支持链接池操作。
- 可扩展性强，XMemcached是基于Java NIO框架 Yanf4j 来实现的，所以在结构上相对清晰，分层明确。

### 案例实践

#### 1. 整合SpringBoot

##### （1）Maven依赖

```xml
<!--memcache-->
<!-- https://mvnrepository.com/artifact/com.googlecode.xmemcached/xmemcached -->
<dependency>
    <groupId>com.googlecode.xmemcached</groupId>
    <artifactId>xmemcached</artifactId>
    <version>2.4.5</version>
</dependency>

<!--使用注解处理器来生成自己的元数据:参考：https://www.jianshu.com/p/57fec884c017-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```

##### （2）基本配置

当然在添加配置文件的时候，SpringBoot默认没有支持自动配置所以需要使用SpringBoot提供的配置文件机制来编写自己的配置文件

- **application.yml添加配置:**

```yml
# ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ memcache 配置 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ #
spring:    
    memcache:
        # memcached服务器节点
        servers: 127.0.0.1:11211
        # nio连接池的数量
        poolSize: 10
        # 设置默认操作超时
        opTimeout: 3000
        # 是否启用url encode机制
        sanitizeKeys: false
```

- **新建 XMemcachedProperties 类，用于保存yml中的配置** 

```java
@Data
@Component
@ConfigurationProperties(prefix = "spring.memcached")
public class XMemcachedProperties {

    /**
     * memcached服务器节点
     */
    private String servers;

    /**
     * nio连接池的数量
     */
    private Integer poolSize;

    /**
     * 设置默认操作超时
     */
    private Long opTimeout;

    /**
     * 是否启用url encode机制
     */
    private Boolean sanitizeKeys;
}
```

- **新建 MemcachedConfig 初始化配置** 

```java
@Slf4j
@Configuration
public class MemcachedConfig {

    @Resource
    private XMemcachedProperties xMemcachedProperties;

    @Bean
    public MemcachedClient getMemcachedClinet(){
        MemcachedClient memcachedClient = null;
        try {
            MemcachedClientBuilder builder = 
                new XMemcachedClientBuilder(AddrUtil.getAddresses(xMemcachedProperties.getServers()));
            builder.setFailureMode(false);
            builder.setSanitizeKeys(xMemcachedProperties.getSanitizeKeys());
            builder.setConnectionPoolSize(xMemcachedProperties.getPoolSize());
            builder.setOpTimeout(xMemcachedProperties.getOpTimeout());
            builder.setSessionLocator(new KetamaMemcachedSessionLocator());
            builder.setCommandFactory(new BinaryCommandFactory());
            memcachedClient = builder.build();
        }catch (IOException e){
            log.error("init MemcachedClient failed:", e);
        }
        return memcachedClient;
    }

}
```

##### （3）使用

- **测试基类** 

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseApplicationTests {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    private Long time;

    @Before
    public void setUp() {
        this.time = System.currentTimeMillis();
        log.info("==> 测试开始执行 <==");
    }

    @After
    public void tearDown() {
        log.info("==> 测试执行完成，耗时：{} ms <==", System.currentTimeMillis() - this.time);
    }
}
```

- **简单的测试** 

```java
/**
 * Memcached 测试
 */
public class MemcachedTest extends BaseApplicationTests {

	@Autowired
	private MemcachedClient memcachedClient;

	@Test
	public void test() throws InterruptedException, TimeoutException, MemcachedException {
		// 放入缓存
		boolean flag = memcachedClient.set("a", 0,  1);

		// 取出缓存
		Object a = memcachedClient.get("a");
		log.warn("a is [{}]", a);

		// 3s后过期
		memcachedClient.set("b", 3, 2);
		Object b = memcachedClient.get("b");
		log.warn("b is [{}]", b);

		Thread.sleep(3000);
		b = memcachedClient.get("b");
		log.warn("b is [{}]", b);
	}
}
```

**执行结果** 

```powershell
 com.wxw.test.TestMemcached               : ==> 测试开始执行 <==
 com.wxw.test.TestMemcached               : a is [1]
 com.wxw.test.TestMemcached               : b is [2]
 com.wxw.test.TestMemcached               : b is [null]
 com.wxw.test.TestMemcached               : ==> 测试执行完成，耗时：3083 ms <==
```

**相关文章**

1. [xmemcached Github](https://github.com/killme2008/xmemcached) 
2. [深入使用XMemcached](http://www.imooc.com/article/256593) 
3. memcache及使用场景































