package com.wxw.common;

import com.wxw.BatchTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.api.chunk.ItemReader;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/7/8
 */
@Slf4j
public class SimpleTest extends BatchTest {

    @Test
    public void test_1() {
        ItemReader itemReader = new AbstractItemReader() {
            @Override
            public Object readItem() throws Exception {
                return "";
            }
        };
    }
}
