package com.wxw.kafka.consumer;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.ser.std.ByteArraySerializer;
import com.wxw.common.helper.CompressHelper;
import com.wxw.kafka.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.zip.*;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/5/11
 */
@Slf4j
public class DemoAmpLogConsumer extends BaseTest {


    // 序列化字节数据
    @Test
    public void test_consumer_crash() throws IOException, DataFormatException {
        String topic = "crashLog";
        Properties props = new Properties();
        // Kafka集群，多台服务器地址之间用逗号隔开
        props.put("bootstrap.servers", "172.16.5.67:9092");
        props.put("group.id", "test_group1");
        // 自动提交offset到zk的时间间隔，时间单位是毫秒
        props.put("auto.commit.interval.ms", "1000");
        // 消息的反序列化类型
        props.put("key.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        Consumer<byte[], byte[]> consumer = new KafkaConsumer<>(props);
        // 订阅的话题
        consumer.subscribe(Arrays.asList(topic));
        // Consumer调用poll方法来轮询Kafka集群的消息，一直等到Kafka集群中没有消息或者达到超时时间100ms为止
        while (true) {
            ConsumerRecords<byte[], byte[]> records = consumer.poll(5);
            for (ConsumerRecord<byte[],byte[]> record : records) {
                log.error("record topic = {}",record.topic());
                log.error("record partition = {} offset = {}",record.partition(),record.offset());
                log.error("record key = {}",record.key());
                log.error("record value = {}",record.value());
                //对record的value进行解析
                // get a zip file instance
                File file = new File("./test.json");
                if (!file.exists()){
                    file.createNewFile();
                }
                OutputStream outputStream = new FileOutputStream(file);
                byte[] uncompress = CompressHelper.uncompress(record.value());
                outputStream.write(uncompress);
                outputStream.flush();

                String string = CompressHelper.uncompressToString(record.value(), "utf-8");
                log.info("string = {}",string);
            }
        }
    }


    @Test
    public void test_producer_crash() throws IOException {
        String topic = "crashLog";
        Properties props = new Properties();
        // Kafka集群，多台服务器地址之间用逗号隔开
        props.put("bootstrap.servers", "172.16.5.67:9092");
        props.put("group.id", "test_group1");
        props.put("ack", "1");
        props.put("retries", "0");
        // 生产消息序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        Producer<String,String> producer = new KafkaProducer<String, String>(props);
        String path = DemoAmpLogConsumer.class.getResource("/").getPath()+File.separator + "apm.json";
        InputStream in = new FileInputStream(path);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.copy(in,outputStream);
        ProducerRecord producerRecord = new ProducerRecord<String,byte[]>(topic,outputStream.toByteArray());
//        ProducerRecord producerRecord = new ProducerRecord<String,String>(topic,"测试");
        Future send = producer.send(producerRecord);
        log.error(JSON.toJSONString(send));
    }
}
