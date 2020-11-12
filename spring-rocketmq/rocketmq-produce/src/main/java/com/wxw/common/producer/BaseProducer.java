package com.wxw.common.producer;

import com.wxw.common.config.RocketMQProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.remoting.RPCHook;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author ：wxw.
 * @date ： 17:30 2020/11/12
 * @description：接受者
 * @version: v_0.0.1
 */
@Slf4j
public abstract class BaseProducer {
    @Autowired
    protected RocketMQProperties rocketMQProperties;

    protected DefaultMQProducer producer;

    @PostConstruct
    public void init() {
        producer = new DefaultMQProducer(getGroup(), getAclRPCHook());
        producer.setNamesrvAddr(rocketMQProperties.getNameServer());
        configProducer();
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        log.info("Xxx生产者启动");
    }

    @PreDestroy
    public void shutdown() {
        log.info("关闭Xxx生产者");
        producer.shutdown();
    }

    protected RPCHook getAclRPCHook() {
        return new AclClientRPCHook(new SessionCredentials(rocketMQProperties.getAccessKey(), rocketMQProperties.getSecretKey()));
    }

    public void configProducer(){

    }

    abstract String getGroup();

    abstract String getTopic();
}
