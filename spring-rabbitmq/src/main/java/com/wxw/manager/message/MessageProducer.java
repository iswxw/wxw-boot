package com.wxw.manager.message;

import com.wxw.common.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ：wxw.
 * @date ： 13:16 2021/1/25
 * @description：消息发布者
 * @link:
 * @version: v_0.0.1
 */
@Component
public class MessageProducer {
    private static Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String routingKey, Object content) throws Exception {
        logger.info("发布消息，routingKey：" + routingKey);
        MessageProperties messageProperties = new MessageProperties();
        // 发送消息时配置消息为持久化，这个不配置时如果broker宕机消息会丢失。
        // 同时还需要确保exchange和queue的durable属性为true，目前使用默认exchange是持久化的，queue自己创建时确定。
        // 默认的消费者设置不是持久化订阅模式，所以多个节点不会重复消费。
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        // 增加过期时间
        messageProperties.setContentType("UTF-8");
        byte[] contentBytes = ObjectUtil.objectToBytes(content);
        Message message = new Message(contentBytes, messageProperties);
        rabbitTemplate.send(routingKey, message);
    }
}
