package com.wxw.common.consumer.retry;

import common.config.JmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

/**
 * @author ：wxw.
 * @date ： 14:53 2020/12/4
 * @description：消费端重试  1S,5S,10S,30S,1M,2M····2H 这样的间隔重试
 * @link:
 * @version: v_0.0.1
 */
@Slf4j
@Component
public class RetryConsumer {

    /**
     * 消费者实体
     */
    private DefaultMQPushConsumer consumer;

    /**
     * 消费者组
     */
    public static final String CONSUMER_GROUP = "wxw_retry_test";

    public static void main(String[] args) throws MQClientException {
        RetryConsumer retryConsumer = new RetryConsumer();
        // System.out.println(retryConsumer);
    }

    /**
     * 通过构造函数 实例化对象
     */
    public RetryConsumer() throws MQClientException {
        AclClientRPCHook clientRPCHook = new AclClientRPCHook(new SessionCredentials(JmsConfig.ACCESS_Key, JmsConfig.SECRET_Key));
        consumer = new DefaultMQPushConsumer(CONSUMER_GROUP,clientRPCHook,new AllocateMessageQueueAveragely());
        consumer.setNamesrvAddr(JmsConfig.NAME_SERVER);
        //订阅topic和 tags（ * 代表所有标签)下信息
        consumer.subscribe(JmsConfig.TOPIC, "*");
        //注册消费的监听 并在此监听中消费信息，并返回消费的状态信息
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            //1、获取消息
            Message msg = msgs.get(0);
            try {
                //2、消费者获取消息
                String body = new String(msg.getBody(), "utf-8");
                //3、获取重试次数
                int count = ((MessageExt) msg).getReconsumeTimes();
                log.info("当前消费重试次数为 = {}", count);

                //case1:超时 重试 [这里睡眠60秒]
                Thread.sleep(60000);
                log.info("休眠60秒 看还能不能走到这里。topic={},keys={},msg={}", msg.getTopic(), msg.getKeys(), body);

                //4、这里设置重试大于3次 那么通过保存数据库 人工来兜底
                if (count >= 2) {
                    log.info("该消息已经重试次数为 = 3 次,保存数据库。topic={},keys={},msg={}", msg.getTopic(), msg.getKeys(), body);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                //case2: 直接抛出异常 来重试
                throw new Exception("=======这里出错了============");
                //return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        //启动监听
        consumer.start();
    }



}
