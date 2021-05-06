package com.wxw.services.jobhandler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author ：wxw.
 * @date ： 8:57 2020/12/21
 * @description：任务Handler示例（Bean模式 方法模式）
 *   1、任务开发：在Spring Bean实例中，开发Job方法；
 *   2、注解配置：为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法",
 *       destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 *   3、执行日志：需要通过 "XxlJobHelper.log" 打印执行日志；
 *   4、任务结果：默认任务结果为 "成功" 状态，不需要主动设置；如有诉求，比如设置任务结果为失败，可以通过 "XxlJobHelper.handleFail/handleSuccess" 自主设置任务结果；
 * @link:
 * @version: v_0.0.1
 */
@Component
public class XxxMethodJobDemo {

    @XxlJob("xxlMethodJobDemo")
    public void xxlMethodJobDemo(){

        XxlJobHelper.log("基于方法的定时任务开始执行,XXL-JOB, Hello World.");
    }
}
