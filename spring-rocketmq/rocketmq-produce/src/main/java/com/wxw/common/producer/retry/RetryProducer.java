package com.wxw.common.producer.retry;

import com.wxw.common.config.RocketMQProperties;
import common.config.JmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.acl.plain.PlainPermissionManager;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.RPCHook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：wxw.
 * @date ： 11:36 2020/12/4
 * @description：重试生产者
 * @link: https://www.cnblogs.com/qdhxhz/p/11117379.html
 * @version: v_0.0.1
 */
@Slf4j
@Component
public class RetryProducer {

   private RPCHook rpcHook = null;

    RetryProducer(){ rpcHook = new AclClientRPCHook(new SessionCredentials(JmsConfig.ACCESS_Key,JmsConfig.SECRET_Key)); }
    /**
     * 生产者组
     */
    private static String PRODUCE_GROUP = "wxw_retry_test";

    public static void main(String[] args) throws Exception {
        // PlainPermissionManager 权限管理
        RetryProducer retryProducer = new RetryProducer();
        //1、创建生产者对象
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCE_GROUP,retryProducer.rpcHook);
        //设置重试次数为10次 (默认2次）
        producer.setRetryTimesWhenSendFailed(10);
        //绑定name server
        producer.setNamesrvAddr(JmsConfig.NAME_SERVER);
        producer.start();
        //创建消息
        Message message = new Message(JmsConfig.TOPIC, ("在RocketMQ新建Topic主题，生成这去匹配" ).getBytes());
        //发送 这里填写超时时间是5毫秒 所以每次都会发送失败
        SendResult sendResult = producer.send(message,3000);
        // RemotingTooMuchRequestException: sendDefaultImpl call timeout
//        SendResult sendResult = producer.send(message,5);
        log.info("输出生产者信息=>{}",sendResult);
    }
}
