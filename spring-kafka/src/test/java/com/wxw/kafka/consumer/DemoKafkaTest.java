package com.wxw.kafka.consumer;

import com.wxw.kafka.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/4/25
 */
@Slf4j
public class DemoKafkaTest {

    public static void main(String[] args) {
        test_consumer_auto_commit();
    }

    /**
     * kafka 临时消费消息 自动提交
     */
    public static void test_consumer_auto_commit() {
        String topic = "videoLogV2";
        Properties props = new Properties();
        // Kafka集群，多台服务器地址之间用逗号隔开
        props.put("bootstrap.servers", "localhost:9092");
        // 消费组ID
        props.put("group.id", "test_group1");
        // Consumer的offset是否自动提交
//        props.put("enable.auto.commit", "true");
        // 自动提交offset到zk的时间间隔，时间单位是毫秒
        props.put("auto.commit.interval.ms", "1000");
        // 消息的反序列化类型
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        Consumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        // 订阅的话题
        consumer.subscribe(Arrays.asList(topic));
        // Consumer调用poll方法来轮询Kafka集群的消息，一直等到Kafka集群中没有消息或者达到超时时间100ms为止
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord record : records) {
                log.info("record topic = {}",record.topic());
                log.info("record partition = {} offset = {}",record.partition(),record.offset());
                log.info("record key = {}",record.key());
                log.info("record value = {}",record.value());
                if(record.value().toString().contains("info_type = 84")){
                    log.info("======================================");
                }
            }
        }
    }

    /**
     * kafka 临时消费消息 手动提交
     */
    public static void testConsumer_manual_commit() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("foo", "bar"));
        final int minBatchSize = 200;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                buffer.add(record);
            }
            if (buffer.size() >= minBatchSize) {
                // insertIntoDb(buffer);
                // 省略处理逻辑
                consumer.commitSync();
                // 清除缓存
                buffer.clear();
            }
        }
    }

}
