package com.wxw.kafka;

import com.wxw.domain.TimerCount;
import org.junit.After;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/4/25
 */
@SpringBootTest
public class BaseTest {

    @Before
    public void test_before() {
        TimerCount.startTime = System.currentTimeMillis();
    }

    @After
    public void test_after() {
        TimerCount.endTime = System.currentTimeMillis();
        TimerCount.print();
    }

}
