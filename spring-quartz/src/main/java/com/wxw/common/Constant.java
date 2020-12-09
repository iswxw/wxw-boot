package com.wxw.common;


/**
 * @author ：wxw.
 * @date ： 11:33 2020/11/4
 * @description：常量枚举
 * @version: v_0.0.1
 */
public class Constant {


    
    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}