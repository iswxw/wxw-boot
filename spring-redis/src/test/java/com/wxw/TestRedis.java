package com.wxw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @contract: 公众号：技术能量站
 * @desc:
 * @link:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedis {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void TestHello(){
        redisTemplate.opsForValue().set("k1","v1");
    }

}
