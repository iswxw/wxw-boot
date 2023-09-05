package com.wxw.controller;

import com.wxw.common.utils.UtilsRedis;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @contract: 公众号：技术能量站
 * @desc:
 * @link:
 */
@RequestMapping("/redis")
@RestController
public class RedisController {

    @Resource
    private UtilsRedis redisUtil;

    @RequestMapping("set")
    public boolean redisset(String key, String value){
        System.out.println(key+"--"+value);
        return redisUtil.set(key,value);
    }

    @RequestMapping("get")
    public Object redisget(String key){
        System.out.println(redisUtil.get(key));
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key,long ExpireTime){
        return redisUtil.expire(key,ExpireTime);
    }

}
