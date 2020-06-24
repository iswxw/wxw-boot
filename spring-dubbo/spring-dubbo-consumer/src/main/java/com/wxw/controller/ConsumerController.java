package com.wxw.controller;

import com.wxw.service.ConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ Author ：wxw.
 * @ Date ： 14:04 2020/6/24
 * @ Description：消费者测试接口
 * @ Version:   v_0.0.1
 */
@RestController
public class ConsumerController {

    @Resource
    private ConsumerService consumerService;

    /**
     * http://localhost:10010/consumer/test
     * @return
     */
    @GetMapping("/consumer/test")
    public String testDate(){
        String name = consumerService.getName();
        return "提供者提供的数据："+name;
    }
}
