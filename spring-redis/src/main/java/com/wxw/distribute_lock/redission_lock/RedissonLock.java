package com.wxw.distribute_lock.redission_lock;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author ：wxw.
 * @date ： 11:58 2020/12/1
 * @description：Redission 分布式锁
 * @version: v_0.0.1
 */
public class RedissonLock {

    public static RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379")
                .setDatabase(6);
        RedissonClient client = Redisson.create(config);
        return client;
    }
}
