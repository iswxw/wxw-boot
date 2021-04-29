package com.wxw.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxw.service.RestDemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/4/28
 */
@RestController
public class RestDemoController {

    @Resource
    private RestDemoService restDemoService;

    private static final String AUTHORIZATION = "Authorization";

    @PostMapping("/ebs/{version}/volumes/{volume_id}/action/attach")
    public ResponseEntity<JSONObject> attach(@PathVariable("volume_id") String volume_id,
                                 @RequestBody JSONObject object,
                                 HttpServletRequest request) {
        String keycloakToken = request.getHeader(AUTHORIZATION);
        //do something......
        //正确返回200
        return restDemoService.attachVolume(object, request, volume_id);
    }

    @DeleteMapping("/ebs/{version}/volumes/{volume_id}/soft")
    public Object softDeleteVolume(@PathVariable(value = "volume_id") String volumeId,
                                        @RequestBody String orderJson,
                                        HttpServletRequest request) {
        String keycloakToken = request.getHeader(AUTHORIZATION);
        JSONObject object = new JSONObject();
        object.put("volumeId",volumeId);
        object.put("orderJson",orderJson);
        object.put("keycloakToken",keycloakToken);
        //正确返回201，消息体为{"code":"202","message":null,"data":null}
        return restDemoService.softDeleteVolume(volumeId,orderJson,keycloakToken);
    }
}
