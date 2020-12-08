package com.wxw.common.consumer.order;

import common.config.JmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

/**
 * @author ：wxw.
 * @date ： 17:19 2020/12/4
 * @description：顺序消费 消费者
 * @link:
 * @version: v_0.0.1
 */
@Slf4j
@Component
public class OrderConsumer {
    /**
     * 消费者实体对象
     */
    private DefaultMQPushConsumer consumer;
    /**
     * 消费者组
     */
    public static final String CONSUMER_GROUP = "wxw_consumer_group";
    /**
     * 通过构造函数 实例化对象
     */
    public OrderConsumer() throws MQClientException {
        consumer = new DefaultMQPushConsumer(CONSUMER_GROUP);
        consumer.setNamesrvAddr("IP:9876");
        //TODO 这里真的是个坑,我product设置VipChannelEnabled(false)，但消费者并没有设置这个参数，之前发送普通消息的时候也没有问题。能正常消费。
        //TODO 但在顺序消息时，consumer一直不消费消息了，找了好久都没有找到原因，直到我这里也设置为VipChannelEnabled(false)，竟然才可以消费消息。
        consumer.setVipChannelEnabled(false);
        //订阅主题和 标签（ * 代表所有标签)下信息
        consumer.subscribe(JmsConfig.TOPIC, "*");
        //注册消费的监听 这里注意顺序消费为MessageListenerOrderly 之前并发为ConsumeConcurrentlyContext
        consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
            //获取消息
            MessageExt msg = msgs.get(0);
            //消费者获取消息 这里只输出 不做后面逻辑处理
            log.info("Consumer-线程名称={},消息={}", Thread.currentThread().getName(), new String(msg.getBody()));
            return ConsumeOrderlyStatus.SUCCESS;
        });
        consumer.start();
    }
}
