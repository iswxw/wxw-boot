package com.wxw.common.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单号生成器
 * @author: wxw
 * @date: 2020-11-28-16:11
 */
public class OrderNumGenerator {

    // 自增长序列
    private int i = 0;

    // 按照 “年-月-日-小时-分钟-秒-自增长序列” 规则生成订单号
    public String getOrderNum(){
        final Date now = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");
        return sdf.format(now)+ ++i;
    }
}
