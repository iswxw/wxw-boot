package com.wxw.controller;

import com.wxw.common.producer.simple.Producer;
import common.config.JmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：wxw.
 * @date ： 10:53 2020/12/4
 * @description：简单实现基本的消息发送测试
 * @link: https://www.cnblogs.com/qdhxhz/p/11109696.html
 * @version: v_0.0.1
 */
@Slf4j
@RestController
@RequestMapping("ap1/v1")
public class SimpleProduceRocketMQController {

    @Autowired
    private Producer producer;

    private List<String> mesList;

    @RequestMapping("/rocket-mq/simple")
    public Object callback() throws Exception {
        //总共发送五次消息
        for (String s : mesList) {
            //创建生产信息
            Message message = new Message(JmsConfig.TOPIC, "test_tag", ("小小一家人的称谓:" + s).getBytes());
            //发送
            SendResult sendResult = producer.getProducer().send(message);
            log.info("输出生产者信息={}",sendResult);
        }
        return "成功";
    }

    /**
     * 初始化消息
     */
    public SimpleProduceRocketMQController() {
        mesList = new ArrayList<>();
        mesList.add("小小");
        mesList.add("爸爸");
        mesList.add("妈妈");
        mesList.add("爷爷");
        mesList.add("奶奶");
    }
}
