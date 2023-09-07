package com.wxw.function.distribute_lock.redission_lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author ：wxw.
 * @date ： 11:56 2020/12/1
 * @description：所测试
 * @version: v_0.0.1
 */
public class RedissonTest {
    public static void main(String[] args) {
        RedissonClient redissonClient = RedissonLock.redissonClient();
        RLock lock = redissonClient.getLock("lockName");
        try {
            // 1. 最常见的使用方法
            //lock.lock();
            // 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
            //lock.lock(10, TimeUnit.SECONDS);
            // 3. 尝试加锁，最多等待2秒，上锁以后8秒自动解锁
            boolean res = lock.tryLock(2, 8, TimeUnit.SECONDS);
            if (res) { //成功
                //处理业务
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();

        }
    }
}
