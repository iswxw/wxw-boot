package com.wxw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/7/26
 */
@RestController
public class defaultClass {

    /**
     * curl http://localhost:22726/
     * @return
     */
    @GetMapping("/")
    public String defaultClass(){
        return "实时监控,保证应用安全!";
    }
}
