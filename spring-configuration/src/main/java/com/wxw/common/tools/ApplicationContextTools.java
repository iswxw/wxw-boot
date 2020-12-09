package com.wxw.common.tools;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author ：wxw.
 * @date ： 15:27 2020/12/9
 * @description：spring 容器上下文
 * @link:
 * @version: v_0.0.1
 */
@Component
public class ApplicationContextTools implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 设置上下文引用
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {
        ApplicationContextTools.applicationContext = applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    public static Class<? extends Object> getType(String name) {
        return applicationContext.getType(name);
    }
}

