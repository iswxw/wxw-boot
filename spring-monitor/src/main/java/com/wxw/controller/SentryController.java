package com.wxw.controller;

import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：wxw.
 * @date ： 14:40 2021/2/5
 * @description：异常日志监控
 * @link:
 * @version: v_0.0.1
 */
@Slf4j
@RestController
public class SentryController {

    @GetMapping("/get")
    public String getSentry() {
        log.info("路径：SentryController==>getSentry 日志信息: Hello World!");
        try {
            throw new Exception("This is a test.");
        } catch (Exception e) {
            Sentry.captureException(e);
        }
        return "SUCCESS";
    }
}
