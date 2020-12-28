package com.wxw.common.conf;

import com.wxw.common.properties.XMemcachedProperties;
import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author ：wxw.
 * @date ： 8:53 2020/12/28
 * @description：Memcached 配置
 * @link:
 * @version: v_0.0.1
 */
@Slf4j
@Configuration
public class MemcachedConfig {

    @Resource
    private XMemcachedProperties xMemcachedProperties;

    @Bean
    public MemcachedClient getMemcachedClinet(){
        MemcachedClient memcachedClient = null;
        try {
            MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(xMemcachedProperties.getServers()));
            builder.setFailureMode(false);
            builder.setSanitizeKeys(xMemcachedProperties.getSanitizeKeys());
            builder.setConnectionPoolSize(xMemcachedProperties.getPoolSize());
            builder.setOpTimeout(xMemcachedProperties.getOpTimeout());
            builder.setSessionLocator(new KetamaMemcachedSessionLocator());
            builder.setCommandFactory(new BinaryCommandFactory());
            memcachedClient = builder.build();
        }catch (IOException e){
            log.error("init MemcachedClient failed:", e);
        }
        return memcachedClient;
    }

}
