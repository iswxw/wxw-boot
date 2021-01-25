package com.wxw.manager.message;

import com.rabbitmq.client.Channel;
import com.wxw.common.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author ：wxw.
 * @date ： 13:33 2021/1/25
 * @description：消息消费者
 * @link:
 * @version: v_0.0.1
 */
@Component
public class MessageConsumer {

    private static Logger logger = LoggerFactory.getLogger(MessageConsumer.class);


    @RabbitListener(queues = "queue.test")
    public void receiveMessage(@Payload byte[] body, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                                              Channel channel) throws Exception {
        try {
            logger.info("==============监听并订阅消息=================");
            Object bytesToObject = ObjectUtil.bytesToObject(body);
            // TODO: 2021/1/25 对订阅后的消息处理
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            logger.error("联系人分享奖励发放消息消费失败 ===>{}",e);
            channel.basicReject(deliveryTag, false);
        }
    }
}
