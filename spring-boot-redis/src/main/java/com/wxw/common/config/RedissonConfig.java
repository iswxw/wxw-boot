package com.wxw.common.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @contract: 公众号：技术能量站
 * @desc: 分布式锁解决方案
 * @link: https://github.com/redisson/redisson
 */
@Configuration
public class RedissonConfig {
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
