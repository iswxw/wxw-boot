package com.wxw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author ：wxw.
 * @date ： 17:12 2020/12/4
 * @description： 订单类实体
 * @link:
 * @version: v_0.0.1
 */
@AllArgsConstructor
@Data
@ToString
public class ProductOrder {
    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 订单类型(订单创建、订单付款、订单完成）
     */
    private String type;
}