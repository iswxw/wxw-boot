package com.wxw.service.impl;

import com.wxw.common.tools.OrderNumGenerator;
import com.wxw.distribute_lock.redission_lock.RedissonLock;
import com.wxw.service.OrderService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import javax.annotation.Resource;

/**
 * @author ：wxw.
 * @date ： 11:14 2020/12/1
 * @description：Redission 实现分布式锁
 * @version: v_0.0.1
 */
public class OrderServiceImplByRedission implements OrderService {

    private OrderNumGenerator ong = new OrderNumGenerator();

    @Resource
    public RedissonClient redissonClient = RedissonLock.redissonClient();

    // 创建订单接口
    @Override
    public void createOrder() {
        // 获取订单号
        String orderNum = null;
        RLock lock = redissonClient.getLock("add-lock");
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
