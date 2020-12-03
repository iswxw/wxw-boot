package com.wxw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：wxw.
 * @date ： 16:15 2020/12/3
 * @description：商品实体信息
 * @version: v_0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produce implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    private Integer produceId;

    /**
     * 商品名称
     */
    private String produceName;

    /**
     * 商品价格
     */
    private Integer price;

    /**
     * 商品库存
     */
    private Integer store;

}

