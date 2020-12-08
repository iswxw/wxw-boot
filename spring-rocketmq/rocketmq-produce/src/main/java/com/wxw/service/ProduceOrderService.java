package com.wxw.service;

/**
 * @author ：wxw.
 * @date ： 16:06 2020/12/3
 * @description：订单业务类
 * @version: v_0.0.1
 */
public interface ProduceOrderService {

    /**
     * @Description: 下单接口
     * @author wxw
     */
    int save(int userId, int produceId, int total);
}
