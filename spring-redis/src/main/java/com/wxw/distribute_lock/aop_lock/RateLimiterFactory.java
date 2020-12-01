package com.wxw.distribute_lock.aop_lock;

import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ Author ：wxw.
 * @ Date ： 11:20 2020/9/28
 * @ Description：限流工厂
 * @ Version:   v_0.0.1
 */
@Component
public class RateLimiterFactory {

    @Resource
    private RedissonClient redissonClient;

    private ConcurrentHashMap<String, RRateLimiter> rRateLimiters = new ConcurrentHashMap<>();

    public RRateLimiter get(String name, long rate) {
        RRateLimiter rRateLimiter = rRateLimiters.get(name);
        if (rRateLimiter == null) {
            rRateLimiter = redissonClient.getRateLimiter(name);
            rRateLimiter.setRate(RateType.OVERALL, rate, 60, RateIntervalUnit.SECONDS);
            rRateLimiters.put(name, rRateLimiter);
        } else {
//            if(rRateLimiter.getConfig().getRate() != rate)
//                rRateLimiter.setRate(RateType.OVERALL, rate, 60, RateIntervalUnit.SECONDS);
        }
        return rRateLimiter;
    }
}
