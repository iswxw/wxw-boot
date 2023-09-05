package com.wxw.common.utils;

import java.util.Random;

/**
 * @contract: 公众号：技术能量站
 * @desc:
 * @link:
 */
public class UtilsID {
    public String gen(){
        Random random = new Random();
        return random.ints().toString();
    }

}
