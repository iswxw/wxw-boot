package com.wxw.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author ：wxw.
 * @date ： 8:51 2020/12/28
 * @description：XMemcached 配置属性，读取的是 yml 文件中 spring.memcached 开头的属性
 * @link:
 * @version: v_0.0.1
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.memcached")
public class XMemcachedProperties {

    /**
     * memcached服务器节点
     */
    private String servers;

    /**
     * nio连接池的数量
     */
    private Integer poolSize;

    /**
     * 设置默认操作超时
     */
    private Long opTimeout;

    /**
     * 是否启用url encode机制
     */
    private Boolean sanitizeKeys;
}
