package com.wxw.common.consumer;

import com.wxw.common.config.RocketMQProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.RPCHook;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author ：wxw.
 * @date ： 17:25 2020/11/12
 * @description：消费者
 * @version: v_0.0.1
 */
@Slf4j
public abstract class BaseConsumer {

    @Autowired
    protected RocketMQProperties rocketMQProperties;

    protected DefaultMQPushConsumer consumer;

    @PostConstruct
    public void init(){
        consumer = new DefaultMQPushConsumer(getGroup(), getAclRPCHook(), new AllocateMessageQueueAveragely());
        consumer.setNamesrvAddr(rocketMQProperties.getNameServer());
        configConsumer();
        try {
            consumer.subscribe(getTopic(), getSubExpression());
            consumer.registerMessageListener(getMessageListener());
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        log.info("Xxx消息者启动");
    }

    @PreDestroy
    public void shutdown() {
        log.info("关闭Xxx消息者");
        consumer.shutdown();
    }

    protected RPCHook getAclRPCHook() {
        return new AclClientRPCHook(new SessionCredentials(rocketMQProperties.getAccessKey(), rocketMQProperties.getSecretKey()));
    }

    public void configConsumer(){

    }

    abstract String getGroup();

    abstract String getTopic();

    abstract String getSubExpression();

    abstract MessageListener getMessageListener();
}

