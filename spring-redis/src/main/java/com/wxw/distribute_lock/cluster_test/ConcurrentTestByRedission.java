package com.wxw.distribute_lock.cluster_test;

import com.wxw.services.OrderService;
import com.wxw.services.impl.OrderServiceImplByRedission;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 模拟分布式集群 并发测试
 * @author: wxw
 * @date: 2020-11-28-16:41
 */
public class ConcurrentTestByRedission {

    public static void main(String[] args) {
        // 并发数
        int currency = 20;
        CyclicBarrier cb = new CyclicBarrier(currency);   // 循环屏障
        CountDownLatch cdl = new CountDownLatch(currency); // 倒计数锁存器

//        OrderService orderService = new OrderServiceImpl();  // 单机
//        OrderService orderServiceLock = new OrderServiceImplWithLock(); // JVM 锁  ReentrantLock

        // 多线程模拟高并发
        for (int i = 0; i < currency; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //模拟分布式集群场景
//                    OrderService orderServiceLock = new OrderServiceImplWithLock(); // 模拟集群 jvm锁 并发导致的问题
                    OrderService orderServiceLock = new OrderServiceImplByRedission(); // 模拟集群 redission 分布式锁
                    System.out.println(Thread.currentThread().getName() + "：我准备好了");
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
