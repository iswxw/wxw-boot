package com.wxw.common.consumer;

import com.alibaba.fastjson.JSON;
import com.wxw.domain.OrderPaidEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ Author ：wxw.
 * @ Date ： 15:42 2020/9/29
 * @ Description：消费者
 * @ Version:   v_0.0.1
 */
@Slf4j
@Component
public class XxxConsumer extends BaseConsumer {

    @Override
    String getGroup() {
        return "XXX_GROUP";
    }

    @Override
    String getTopic() {
        return "XXX_TOPIC";
    }

    @Override
    String getSubExpression() {
        return "*";
    }

//    @Override
//    public void configConsumer() {
//        super.configConsumer();
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//    }

    @Override
    MessageListener getMessageListener() {
        return new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                byte[] body = msgs.get(0).getBody();
//                                msgs.forEach(msg->{
//                    int reconsumeTimes = msg.getReconsumeTimes(); // 重试次数
//                });
//                context.setAckIndex(0); // 手动确认
//                context.setDelayLevelWhenNextConsume(2000); // 延迟消费
                OrderPaidEvent seller = JSON.parseObject(body, OrderPaidEvent.class);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        };
    }

    @Override
    public void configConsumer() {
        super.configConsumer();
//        consumer.setMaxReconsumeTimes(2000);
    }
}
