package com.wxw.common.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author ：wxw.
 * @date ： 13:14 2020/12/21
 * @description：mongo配置
 * @link:
 * @version: v_0.0.1
 */
@Configuration
@EnableMongoRepositories("com.wxw.dao")
public class MongodbConfiguration {

    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create("mongodb://localhost:27017");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "wxw-test");
    }
}
