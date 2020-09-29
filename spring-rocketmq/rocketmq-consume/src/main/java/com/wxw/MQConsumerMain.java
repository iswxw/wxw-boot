package com.wxw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ Author ：wxw.
 * @ Date ： 15:36 2020/9/29
 * @ Description：消费者启动类
 * @ Version:   v_0.0.1
 */
@SpringBootApplication
public class MQConsumerMain {
    public static void main(String[] args) {
        SpringApplication.run(MQConsumerMain.class, args);
    }
}
