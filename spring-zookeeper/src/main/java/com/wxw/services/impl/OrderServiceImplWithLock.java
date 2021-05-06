package com.wxw.services.impl;

import com.wxw.common.tools.OrderNumGenerator;
import com.wxw.services.OrderService;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wxw
 * @date: 2020-11-28-16:32
 */
public class OrderServiceImplWithLock implements OrderService {

    //    private OrderNumGenerator ong = new OrderNumGenerator();
    private static OrderNumGenerator ong = new OrderNumGenerator();

    private Lock lock = new ReentrantLock();

    // 创建订单接口
    @Override
    public void createOrder() {
        // 获取订单号
        String orderNum = null;
        try {
            lock.lock();
            orderNum = ong.getOrderNum();
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName()+":  orderNum = " + orderNum);
        // 业务代码 此处省略100行
    }
}
