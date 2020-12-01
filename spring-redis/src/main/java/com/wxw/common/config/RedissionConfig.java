package com.wxw.common.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @ Author ：wxw.
 * @ Date ： 11:14 2020/9/28
 * @ Description：RedissionConfig 分布式锁配置
 * @ Version:   v_0.0.1
 */
@Configuration
public class RedissionConfig {

    @Value("${redisson.address}")
    private String addressUrl;
    @Value("${redisson.password}")
    private String password;
    @Value("${redisson.database}")
    private Integer database;
    @Value("${redisson.connectionPoolSize}")
    private int connectionPoolSize;
    @Value("${redisson.connectionMinimumIdleSize}")
    private int connectionMinimumIdleSize;
    @Value("${redisson.subscriptionConnectionPoolSize}")
    private int subscriptionConnectionPoolSize;
    @Value("${redisson.subscriptionConnectionMinimumIdleSize}")
    private int subscriptionConnectionMinimumIdleSize;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer()
                .setAddress(addressUrl).setDatabase(database)
                .setConnectionPoolSize(connectionPoolSize)
                .setConnectionMinimumIdleSize(connectionMinimumIdleSize)
                .setSubscriptionConnectionPoolSize(subscriptionConnectionPoolSize)
                .setSubscriptionConnectionMinimumIdleSize(subscriptionConnectionMinimumIdleSize);
        if(StringUtils.isNotBlank(password)){
            singleServerConfig.setPassword(password);
        }
        return Redisson.create(config);
    }
}
