//package com.xxl.job.core.handler.annotation;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Inherited;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
///**
// * annotation for job handler
// *
// * will be replaced by {@link com.xxl.job.core.handler.annotation.XxlJob}
// *
// * @author 2016-5-17 21:06:49
// */
//@Target({ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Inherited
//@Deprecated
//public @interface JobHandler {
//
//    String value();
//
//}

/**
 * 7.30 版本 v2.2.0 Release Notes[2020-04-14]
 * 移除旧类注解JobHandler，推荐使用基于方法注解 “@XxlJob” 的方式进行任务开发；(如需保留类注解JobHandler使用方式，可以参考旧版逻辑定制开发);
 */