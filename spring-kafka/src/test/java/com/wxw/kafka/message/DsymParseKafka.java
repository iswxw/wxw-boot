package com.wxw.kafka.message;

import com.alibaba.fastjson.JSON;
import com.wxw.kafka.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/6/22
 */
@Slf4j
public class DsymParseKafka extends BaseTest {

   public static String topic = "gaotu_dsym_parse_test";

    @Test
    public void test_dsym_parse_consumer() {
        Properties props = new Properties();
        // Kafka集群，多台服务器地址之间用逗号隔开
//        props.put("bootstrap.servers", "localhost:9092");
        props.put("bootstrap.servers", "172.16.18.74:9092");
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
            ConsumerRecords<String, String> records = consumer.poll(1);
            for (ConsumerRecord record : records) {
                log.error("record topic = {}",record.topic());
                log.error("record partition = {} offset = {}",record.partition(),record.offset());
                log.error("record key = {}",record.key());
                log.error("record value = {}",record.value());
            }
        }
    }


    @Test
    public void test_dsym_parse_producer() throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        // Kafka集群，多台服务器地址之间用逗号隔开
        // props.put("bootstrap.servers", "172.16.5.67:9092");
        props.put("bootstrap.servers", "172.16.18.74:9092");
        props.put("group.id", "test_group1");
        props.put("ack", "1");
        props.put("retries", "0");
        // 生产消息序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String,String> producer = new KafkaProducer(props);
        ProducerRecord<String,String> producerRecord = new ProducerRecord(topic,JSON.toJSONString("你好"));
        Future send = producer.send(producerRecord);
        log.info("send = {}",send.get());
        log.error("测试 = {}",JSON.toJSONString(send));
    }
}
