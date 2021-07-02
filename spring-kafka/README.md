### spring-kafka



### 1. 前言

对于使用Apache Kafka的Spring项目，我们在Spring核心提供了Kafka消息的集成。提供了公共的接入“模板”，作为消息发送的高级抽象层，还为消息的POJO提供支持。

### 2. kafka 环境

#### 2.1 安装 zookeeper

```bash
# 下载zookeeper镜像
docker pull wurstmeister/zookeeper

# 启动zookeeper
docker run -d --name zookeeper --publish 2181:2181 --volume /etc/localtime:/etc/localtime wurstmeister/zookeeper
```

#### 2.2 安装 kafka

- kafka默认安装在/opt/kafka

```bash
## 拉去镜像
docker pull wurstmeister/kafka

## 创建kafka容器
docker run -d --name kafka -p 9092:9092 \
-e KAFKA_BROKER_ID=0 \
-e KAFKA_ZOOKEEPER_CONNECT=wxw.plus:2181 \
-e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://wxw.plus:9092 \
-e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 \
--volume /etc/localtime:/etc/localtime \
wurstmeister/kafka
```

相关文章

1. docker 搭建kafka环境：https://www.cnblogs.com/angelyan/p/14445710.html

### 3. spring和kafka集成



相关文章

1. SpringBoot和kafka集成：https://www.orchome.com/6794