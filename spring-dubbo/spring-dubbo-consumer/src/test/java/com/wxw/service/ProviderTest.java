package com.wxw.service;

import com.wxw.service.impl.ConsumerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ Author ：wxw.
 * @ Date ： 21:04 2020/6/8
 * @ Description：测试消费者信息
 * @ Version:   v_0.0.1
 */
@SpringBootTest
public class ProviderTest {

    @Autowired
    private ConsumerServiceImpl consumerServiceImpl;

    @Test
    public void testData1() {
        consumerServiceImpl.getName();
    }
}
