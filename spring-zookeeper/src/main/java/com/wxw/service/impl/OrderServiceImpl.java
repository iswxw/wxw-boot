package com.wxw.service.impl;

import com.wxw.common.tools.OrderNumGenerator;
import com.wxw.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * 订单模块
 * @author: wxw
 * @date: 2020-11-28-16:09
 */
public class OrderServiceImpl implements OrderService {

    private OrderNumGenerator ong = new OrderNumGenerator();

    // 创建订单接口
    @Override
    public void createOrder() {
        // 获取订单号
        String orderNum = ong.getOrderNum();
        System.out.println(Thread.currentThread().getName()+":  orderNum = " + orderNum);
        // 业务代码 此处省略100行
    }
}
