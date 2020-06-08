package com.wxw;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ Author ：wxw.
 * @ Date ： 20:01 2020/6/8
 * @ Description：消费者启动类
 * @ Version:   v_0.0.1
 */
@EnableDubboConfig
@SpringBootApplication
public class ConsumerMain {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerMain.class,args);
    }
}
