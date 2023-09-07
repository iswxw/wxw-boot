package com.wxw.services.impl;

import com.wxw.common.tools.OrderNumGenerator;
import com.wxw.function.distribute_lock.zk_lock.ZKDistributeLock;
import com.wxw.services.OrderService;
import java.util.concurrent.locks.Lock;

/**
 * @author: wxw
 * @date: 2020-11-28-21:35
 */
public class OrderServiceImplWithZkDisLock implements OrderService {

    // 用static 修饰，模拟共用一个订单号
    private static OrderNumGenerator ong = new OrderNumGenerator();

//    private Lock lock = new ReentrantLock();  // JVM 级锁 在单机环境有效，集群环境失效

    // 创建订单接口
    @Override
    public void createOrder() {
        // 获取订单号
        String orderNum = null;
        Lock lock = new ZKDistributeLock("/BigTree11111");  // 临时节点  有惊群效应
//        Lock lock = new ZKDistributeImproveLock("/BigTree22222");  // 临时顺序节点  完美
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
