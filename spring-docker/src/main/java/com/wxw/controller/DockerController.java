package com.wxw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author ：wxw.
 * @ Date ： 10:06 2020/9/30
 * @ Description：Docker整合测试
 * @ Version:   v_0.0.1
 */
@RestController
public class DockerController {

    @GetMapping("/docker-01")
    public String getDocker_01() {
        return "Hello World!";
    }
}
