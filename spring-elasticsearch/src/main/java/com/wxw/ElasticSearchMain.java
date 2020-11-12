package com.wxw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 文章地址：https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.clients.rest
 * @ Author ：wxw.
 * @ Date ： 10:12 2020/8/26
 * @ Description：ES启动类
 * @ Version:   v_0.0.1
 */
@SpringBootApplication
public class ElasticSearchMain {
    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchMain.class, args);
    }
}
