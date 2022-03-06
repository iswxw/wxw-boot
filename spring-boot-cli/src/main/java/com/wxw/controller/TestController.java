package com.wxw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @contract: 公众号：Java半颗糖
 * @desc:
 * @link:
 */
@RestController
public class TestController {

    // curl 127.0.0.1:8080
    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

}
