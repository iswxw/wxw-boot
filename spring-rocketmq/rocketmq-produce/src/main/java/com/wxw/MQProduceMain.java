package com.wxw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ Author ：wxw.
 * @ Date ： 14:20 2020/9/29
 * @ Description：rocketMQ生产者启动类
 * @ Version:   v_0.0.1
 */
@SpringBootApplication
public class MQProduceMain {
    public static void main(String[] args) {
        SpringApplication.run(MQProduceMain.class,args);
    }
}
