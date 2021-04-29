package com.wxw.controller;

import com.wxw.common.constant.ConstantKafka;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: wxw
 * @date: 2021-04-10-15:05
 * @link:
 * @description: 消息生产者
 */
@Slf4j
@RestController
public class ProducerController {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 简单的生产者
     * http://localhost:8080/kafka/normal/%E4%BD%A0%E5%A5%BD
     */
    @GetMapping("/kafka/normal/{message}")
    public void sendMessage1(@PathVariable("message") String normalMessage) {
        log.info("生产消息：{}",normalMessage);
        // topic命名规范：公司部门开头，环境结尾
        kafkaTemplate.send(ConstantKafka.TOPIC_SIMPLE, normalMessage);
    }

    /**
     * ==============带回调的生产者===================
     * http://localhost:8080/kafka/callbackOne/ABCD
     */
    @GetMapping("/kafka/callbackOne/{message}")
    public void sendMessage2(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send(ConstantKafka.TOPIC_CALLBACK, callbackMessage).addCallback(success -> {
            // 消息发送到的topic
            String topic = success.getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = success.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = success.getRecordMetadata().offset();
            System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);
        }, failure -> {
            System.out.println("发送消息失败:" + failure.getMessage());
        });
    }

    @GetMapping("/kafka/callbackTwo/{message}")
    public void sendMessage3(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send(ConstantKafka.TOPIC_CALLBACK, callbackMessage)
                .addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        System.out.println("发送消息失败：" + ex.getMessage());
                    }

                    @Override
                    public void onSuccess(SendResult<String, Object> result) {
                        System.out.println("发送消息成功：" + result.getRecordMetadata().topic() +
                                "-" + result.getRecordMetadata().partition() +
                                "-" + result.getRecordMetadata().offset());
                    }
                });
    }


    /**
     * kafka 事务提交
     */
    @GetMapping("/kafka/transaction")
    public void sendMessageTransaction(){
        // 声明事务：后面报错消息不会发出去
        kafkaTemplate.executeInTransaction(operations -> {
            operations.send(ConstantKafka.TOPIC_TRANSACTION,"test executeInTransaction");
            throw new RuntimeException("fail");
        });
        // 不声明事务：后面报错但前面消息已经发送成功了
        kafkaTemplate.send(ConstantKafka.TOPIC_TRANSACTION,"test executeInTransaction");
        throw new RuntimeException("fail");
    }



}
