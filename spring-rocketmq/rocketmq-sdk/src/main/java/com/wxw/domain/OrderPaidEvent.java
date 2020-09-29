package com.wxw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ Author ：wxw.
 * @ Date ： 15:28 2020/9/29
 * @ Description：
 * @ Version:   v_0.0.1
 */
@Data
@AllArgsConstructor
public class OrderPaidEvent implements Serializable {

    private String orderId;

    private BigDecimal paidMoney;
}