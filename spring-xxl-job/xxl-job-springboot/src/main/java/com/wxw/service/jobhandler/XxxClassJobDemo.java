package com.wxw.service.jobhandler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.IJobHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author ：wxw.
 * @date ： 17:20 2020/12/18
 * @description：任务Handler示例（Bean模式 类模式）----7.30 版本 v2.2.0 Release Notes[2020-04-14] 版本中已经移除
 *  开发步骤：
 *     1、继承"IJobHandler"：“com.xxl.job.core.handler.IJobHandler”；
 *     2、注册到Spring容器：添加“@Component”注解，被Spring容器扫描为Bean实例；
 *     3、注册到执行器工厂：添加“@JobHandler(value="自定义jobhandler名称")”注解，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 *     4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 * @link:
 * @version: v_0.0.1
 */
@Component
@Deprecated
public class XxxClassJobDemo extends IJobHandler {

    @Override
    public void execute() throws Exception {
        XxlJobHelper.log("基于类的定时任务执行：{}", LocalDateTime.now());
    }
}