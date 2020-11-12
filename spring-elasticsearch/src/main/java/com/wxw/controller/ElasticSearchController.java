package com.wxw.controller;

import com.wxw.domain.Person;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ Author ：wxw.
 * @ Date ： 16:37 2020/8/26
 * @ Description：ES的基本操作
 * @ Version:   v_0.0.1
 */
@RestController
public class ElasticSearchController {

    @Resource
    private RestHighLevelClient highLevelClient;

    @PostMapping("/person")
    public String save(@RequestBody Person person) {

        return null;
    }

    @GetMapping("/person/{id}")
    public Person findById(@PathVariable("id")  Long id) {

        return null;
    }
}
