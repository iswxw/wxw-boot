###  Wellcome In Elastic Search 

---
> [Elastic Search 参考文档](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/index.html)    
> [SpringBoot 官方基于ES的文档](https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html)    


  **版本选型**

 elasticsearch-rest-high-level-client方式来集成，发现这有个坑，开始没注意，踩了好久，就是要排除掉elasticsearch、elasticsearch-rest-client，
 这里没有选择spring-boot-starter-data-elasticsearch，因为最新版的starter现在依然是6.x版本号，并没有集成elasticsearch7.4.0，导致使用过程中有很多版本冲突，
 读者在选择的时候多加留意。

  **基本概念**
  
  1. ElasticSearch 客户端
  2. ElasticSearch 对象映射
  3. ElasticSearch 基本操作
  4. ElasticSearch 存储库  

  **主要功能**    
  
  1. 搜索
  2. 纠错(推荐)  
  
  **工具类**
  1. Spring Data Elasticsearch
   - 主要功能：将Java对象（域实体）映射到存储在Elasticsearch中的JSON表示形式并反向映射的过程
  
  ```xml
    <!--Spring Data Elasticsearch Object Mapping是将Java对象（域实体）映射到存储在Elasticsearch中的JSON表示形式并反向映射的过程-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
    </dependency>
  ```    
  2. elasticsearch-rest-high-level-client   
   
   - 官网教程：https://www.elastic.co/guide/en/elasticsearch/client/java-rest/master/java-rest-high-document-index.html
   - 主要功能：Java高级REST客户端在Java高级REST客户端之上工作。公开API特定的方法，这些方法接受请求对象作为参数并返回响应对象，以便请求编组和响应解编组由客户端本身处理
     每个API可以同步或异步调用。同步方法返回一个响应对象，而名称以async后缀结尾的异步方法则需要一个侦听器参数，一旦接收到响应或错误，该参数就会被通知（在低级客户端管理的线程池上）
   ```xml
     <!-- elasticsearach 高级客户端 -->
    <dependency>
        <groupId>org.elasticsearch.client</groupId>
        <artifactId>elasticsearch-rest-high-level-client</artifactId>
        <version>7.4.2</version>
    </dependency>
   ```
  
  