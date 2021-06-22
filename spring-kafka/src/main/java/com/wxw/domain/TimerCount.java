package com.wxw.domain;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/6/11
 */
public class TimerCount {

    public static Long startTime;

    public static Long endTime;

    public static void print() {
        long end1 = endTime - startTime;
        System.out.println("总耗时 = " + end1 + "ms");
        System.out.println("总耗时 = " + end1 / 1000 + "s");
    }
}
