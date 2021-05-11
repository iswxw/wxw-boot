package com.wxw.kafka;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/4/25
 */
@SpringBootTest
public class BaseTest {

    @Test
    public void test_path() {
        System.out.println("BaseTest.class.getResource(\"\") = " + BaseTest.class.getResource("/"));
    }
}
