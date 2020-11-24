package com.wxw.common.producer;

import com.alibaba.fastjson.JSON;
import com.wxw.domain.OrderPaidEvent;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

/**
 * @ Author ：wxw.
 * @ Date ： 15:30 2020/9/29
 * @ Description：RocketMQ生产者
 * @ Version:   v_0.0.1
 */
@Component
public class XxxProducer extends BaseProducer{

    @Override
    String getGroup() {
        return "XXX_GROUP";
    }

    @Override
    String getTopic() {
        return "XXX_TOPIC";
    }

    public void send(OrderPaidEvent orderPaidEvent) {
        send("test_tag", orderPaidEvent.getOrderId(), orderPaidEvent);
    }

    public void send(String tags, String keys, OrderPaidEvent orderPaidEvent) {
        Message msg = new Message(getTopic(), tags, keys, JSON.toJSONBytes(orderPaidEvent));
        SendResult sendResult = null;
        try {
            sendResult = producer.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("响应结果： %s %n", sendResult);
    }
}
