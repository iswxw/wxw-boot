package com.wxw;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ Author ：wxw.
 * @ Date ： 18:58 2020/6/8
 * @ Description：服务提供者
 * @ Version:   v_0.0.1
 */
@EnableDubboConfig
@SpringBootApplication
public class ProviderMian {
    public static void main(String[] args) {
        SpringApplication.run(ProviderMian.class,args);
    }
}
