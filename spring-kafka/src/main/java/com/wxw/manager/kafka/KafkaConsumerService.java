package com.wxw.manager.kafka;

import com.wxw.common.constant.ConstantKafka;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: wxw
 * @date: 2021-04-10-15:17
 * @link:
 * @description:
 */
@Component
public class KafkaConsumerService {

    // 消费监听 普通消息
    @KafkaListener(topics = {ConstantKafka.TOPIC_SIMPLE})
    public void onMessage1(ConsumerRecord<?, ?> record) {
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("简单消费：" + record.topic() + "-" + record.partition() + "-" + record.value());
    }

    // 消费监听带回调消息
    @KafkaListener(topics = {ConstantKafka.TOPIC_CALLBACK})
    public void onMessageCallBack(ConsumerRecord<?, ?> record) {
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println(" 带回调消息消费：" + record.topic() + "-" + record.partition() + "-" + record.value());
    }

    // 消费监听事务消息
    @KafkaListener(topics = {ConstantKafka.TOPIC_TRANSACTION})
    public void onMessageTransaction(ConsumerRecord<?, ?> record) {
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("事务消息消费：" + record.topic() + "-" + record.partition() + "-" + record.value());
    }

    // 指定分区、偏移量消费

    /**
     * @Title 指定topic、partition、offset消费
     * @Description 同时监听topic1和topic2，监听topic1的0号分区、topic2的 "0号和1号" 分区，指向1号分区的offset初始值为8
     * @Author long.yuan
     * @Date 2020/3/22 13:38
     * @Param [record]
     * @return void
     **/
    @KafkaListener(
            id = "consumer1",
            groupId = "felix-group",
            topicPartitions = {
                    @TopicPartition(topic = "topic1", partitions = {"0"}),
                    @TopicPartition(topic = "topic2", partitions = "0", partitionOffsets =
                    @PartitionOffset(partition = "1", initialOffset = "8"))
            })
    public void onMessage2(ConsumerRecord<?, ?> record) {
        System.out.println(
                "topic:" + record.topic() +
                        "|partition:" + record.partition() +
                        "|offset:" + record.offset() +
                        "|value:" + record.value());
    }

    /**
     * 批量消费消息
     * 配置
     * # 设置批量消费
     * spring.kafka.listener.type=batch
     * # 批量消费每次最多消费多少条消息
     * spring.kafka.consumer.max-poll-records=50
     * @param records
     */
    @KafkaListener(id = "consumer2",groupId = "felix-group", topics = "topic1")
    public void onMessage3(List<ConsumerRecord<?, ?>> records) {
        System.out.println(">>>批量消费一次，records.size()="+records.size());
        for (ConsumerRecord<?, ?> record : records) {
            System.out.println(record.value());
        }
    }


    /**
     * 消息转发
     */

    @KafkaListener(topics = {ConstantKafka.TOPIC_CALLBACK})
    @SendTo(ConstantKafka.TOPIC_SIMPLE)
    public String onMessage7(ConsumerRecord<?, ?> record) {
        return record.value()+"-forward message";
    }



}
