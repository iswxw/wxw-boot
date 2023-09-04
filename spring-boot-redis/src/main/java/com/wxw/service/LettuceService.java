package com.wxw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URL;

/**
 * @contract: 公众号：技术能量站
 * @desc:
 * @link:
 */
@Service
public class LettuceService {

    // inject the actual template
    @Resource
    private RedisTemplate<String, String> template;

    public void addLink(String userId, URL url) {
        template.opsForValue().set("k1","v1");
    }

}
