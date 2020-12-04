package com.wxw.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：wxw.
 * @date ： 16:12 2020/12/3
 * @description：连接RocketMQ服务器实体
 * @version: v_0.0.1
 */
@Data
@Configuration
public class Jms {

    /**
     * 配置中心读取 服务器地址
     */
    @Value("${name_server:10.1.1.75:9876}")
    private String nameServer;

    /**
     * 配置中心读取 主题
     */
    @Value("${order_topic:topic}")
    private String orderTopic;

}
