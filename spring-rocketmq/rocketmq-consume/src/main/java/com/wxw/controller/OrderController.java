package com.wxw.controller;

import com.alibaba.fastjson.JSON;
import com.wxw.services.ProduceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：wxw.
 * @date ： 16:22 2020/12/3
 * @description：订单服务相关接口
 * @version: v_0.0.1
 */
@Slf4j
@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private ProduceService produceService;

    /**
     * 根据主键ID获取商品
     */
    @GetMapping("/find")
    public String findById(@RequestParam(value = "produceId") int produceId) {
        return JSON.toJSONString(produceService.findById(produceId));

    }


}
