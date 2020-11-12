package com.wxw.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ：wxw.
 * @date ： 17:27 2020/11/12
 * @description：MQ配置信息
 * @version: v_0.0.1
 */
@Component
@ConfigurationProperties(prefix = "rocketmq")
@Data
public class RocketMQProperties {
    private String nameServer;
    private String accessKey;
    private String secretKey;
}