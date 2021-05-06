package com.wxw.distribute_lock.cluster_test;

import com.wxw.services.OrderService;
import com.wxw.services.impl.OrderServiceImplWithLock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 20*20  集群 并发问题
 * @author: wxw
 * @date: 2020-11-30-22:39
 */
public class ConcurrentTestDemo_JvmLock {
    // 模拟并发无法保证 订单号的唯一性
    public static void main(String[] args) {
        int currency = 20;
        CyclicBarrier cb = new CyclicBarrier(currency);  // 循环屏障
        CountDownLatch cdl = new CountDownLatch(currency); // 倒计数锁存器
//        OrderService orderService = new OrderServiceImpl(); //单机无锁
//        OrderService orderServiceLock = new OrderServiceImplWithLock(); // 单机 jvm锁想

        // 多线程模拟高并发
        for (int i = 0; i < currency; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 模拟分布式集群场景
                    // 模拟集群 jvm锁 也导致并发问题
                    OrderService orderServiceLock = new OrderServiceImplWithLock();
                    System.out.println(Thread.currentThread().getName() + "：我准备好");
                    // 等待一起出发
                    try {
                        // 方式一
                        cb.await();
                        // 方式二
//                        cdl.countDown();
//                        cdl.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    // 调用创建订单服务
//                    orderService.createOrder();
                    orderServiceLock.createOrder();
                }
            }).start();
        }
    }
}
