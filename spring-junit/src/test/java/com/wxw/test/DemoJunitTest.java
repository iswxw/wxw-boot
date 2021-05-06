package com.wxw.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
// 静态引入
import static org.mockito.Mockito.*;

/**
 * @author weixiaowei
 * @desc: 普通单元测试
 * @date: 2021/4/27
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class DemoJunitTest {

    /**
     * 验证行为是否发生
     */
    @Test
    public void test_1() {
        //模拟创建一个List对象
        List<Integer> mock = mock(List.class);
        //调用mock对象的方法
        mock.add(1);
        log.info("mock = {}",mock);
        mock.clear();
        //验证方法是否执行
        verify(mock).add(1);
        verify(mock).clear();
    }

    /**
     * 多次触发返回不同值
     */
    @Test
    public void test_2() {
        //mock一个Iterator类
        Iterator iterator = mock(Iterator.class);
        //预设当iterator调用next()时第一次返回hello，第n次都返回world
        Mockito.when(iterator.next()).thenReturn("hello").thenReturn("world");
        //使用mock的对象
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();
        //验证结果
        Assert.assertEquals("hello world world", result);
    }

    /**
     * 模拟抛出异常
     */
    @Test(expected = IOException.class)//期望报IO异常
    public void when_thenThrow() throws IOException{
        OutputStream mock = Mockito.mock(OutputStream.class);
        //预设当流关闭时抛出异常
        Mockito.doThrow(new IOException()).when(mock).close();
        mock.close();
    }

    /**
     * 校验
     */
    @Test
    public void test_verify() {
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.add("two");
        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");
        when(mockedList.size()).thenReturn(5);
        int size = mockedList.size();
        log.info("size = {}",size);
        Assert.assertEquals(size, 5);
    }
}
