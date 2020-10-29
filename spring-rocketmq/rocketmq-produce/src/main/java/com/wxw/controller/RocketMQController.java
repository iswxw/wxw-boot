package com.wxw.controller;

import com.wxw.common.mq.MQProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：wxw.
 * @date ： 14:35 2020/10/29
 * @description：rocketMQ测试发布
 * @version: v_0.0.1
 */
@RestController
public class RocketMQController {


    @Resource
    private MQProducer mqProducer;

    @GetMapping("/get")
    public String testGet(){
        try {
            mqProducer.sendMessage("我是来测试的！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "测试成功";
    }
}
