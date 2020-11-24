package com.wxw.controller;

import com.wxw.common.producer.XxxProducer;
import com.wxw.domain.OrderPaidEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author ：wxw.
 * @date ： 14:35 2020/10/29
 * @description：rocketMQ测试发布
 * @version: v_0.0.1
 */
@RestController
public class RocketMQController {


    @Resource
    private XxxProducer mqProducer;

    @GetMapping("/get")
    public String testGet(){
        try {
            OrderPaidEvent paidEvent = new OrderPaidEvent("001", new BigDecimal(100));
            mqProducer.send(paidEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "测试成功";
    }
}
