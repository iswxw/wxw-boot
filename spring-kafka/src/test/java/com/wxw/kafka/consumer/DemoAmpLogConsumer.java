package com.wxw.kafka.consumer;

import cn.hutool.core.util.ZipUtil;
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
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.zip.DataFormatException;

/**
 * @author weixiaowei
 * @desc: 文件压缩为字节数组使用kafka传递
 * @date: 2021/5/11
 */
@Slf4j
public class DemoAmpLogConsumer extends BaseTest {

    /**
     * 消费字节数组（二进制文件流）
     * 序列化字节数据
     * @throws IOException
     */
    @Test
    public void test_consumer_crash() throws IOException {
        String topic = "crash_ios_Log";
        Properties props = new Properties();
        // Kafka集群，多台服务器地址之间用逗号隔开
        props.put("bootstrap.servers", "172.16.5.67:9092");
        props.put("group.id", "test_group1");
        // 自动提交offset到zk的时间间隔，时间单位是毫秒
        props.put("auto.commit.interval.ms", "1000");
        // 消息的反序列化类型
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        Consumer<String, byte[]> consumer = new KafkaConsumer<>(props);
        // 订阅的话题
        consumer.subscribe(Arrays.asList(topic));
        // Consumer调用poll方法来轮询Kafka集群的消息，一直等到Kafka集群中没有消息或者达到超时时间100ms为止
        while (true) {
            ConsumerRecords<String, byte[]> records = consumer.poll(100);
            for (ConsumerRecord<String,byte[]> record : records) {
                log.error("record topic = {}",record.topic());
                log.error("record partition = {} offset = {}",record.partition(),record.offset());
                log.error("record key = {}",record.key());
                log.error("record value = {}",record.value());
                //对record的value进行解析
                // get a zip file instance
                File file = new File("./test"+System.currentTimeMillis()+".json");
                if (!file.exists()){
                    file.createNewFile();
                }
                OutputStream outputStream = new FileOutputStream(file);
                byte[] uncompress = ZipUtil.unGzip(record.value());
                outputStream.write(uncompress);
                outputStream.flush();
//                outputStream.close();
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
        ProducerRecord producerRecord = new ProducerRecord<String,byte[]>(topic,ZipUtil.gzip(outputStream.toByteArray()));
//        ProducerRecord producerRecord = new ProducerRecord<String,String>(topic,"测试");
        Future send = producer.send(producerRecord);
        log.error(JSON.toJSONString(send));
    }


    @Test
    public void test_path() {
        System.out.println("BaseTest.class.getResource(\"\") = " + BaseTest.class.getResource("/"));
    }

    // 序列化字节数据
    @Test
    public void test_consumer_base64() throws IOException, DataFormatException {
        String topic = "crashLog";
        Properties props = new Properties();
        // Kafka集群，多台服务器地址之间用逗号隔开
        props.put("bootstrap.servers", "172.16.5.67:9092");
        props.put("group.id", "test_group1");
        // 自动提交offset到zk的时间间隔，时间单位是毫秒
        props.put("auto.commit.interval.ms", "1000");
        // 消息的反序列化类型
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        Consumer<String,String> consumer = new KafkaConsumer<>(props);
        // 订阅的话题
        consumer.subscribe(Arrays.asList(topic));
        // Consumer调用poll方法来轮询Kafka集群的消息，一直等到Kafka集群中没有消息或者达到超时时间100ms为止
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                log.error("record topic = {}",record.topic());
                log.error("record partition = {} offset = {}",record.partition(),record.offset());
                log.error("record key = {}",record.key());
                log.error("record value = {}",record.value());
                //对record的value进行解析
                // get a zip file instance
                File file = new File("./test"+System.currentTimeMillis()+".json");
                if (!file.exists()){
                    file.createNewFile();
                }
                OutputStream outputStream = new FileOutputStream(file);
//                byte[] decode = Base64.decode(record.value().getBytes(StandardCharsets.UTF_8));
//                outputStream.write(decode);
//                outputStream.flush();

//                byte[] uncompress = CompressHelper.uncompress(decode);
                byte[] uncompress = ZipUtil.unGzip(record.value().getBytes(StandardCharsets.UTF_8));
                outputStream.write(uncompress);
                outputStream.flush();
//                outputStream.close();
            }
        }
    }


}
