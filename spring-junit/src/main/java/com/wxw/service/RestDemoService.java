package com.wxw.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/4/28
 */
@Service
public class RestDemoService {

    public ResponseEntity<JSONObject> attachVolume(JSONObject object, HttpServletRequest request, String keycloakToken) {
        object.put("JSONObject",object);
        object.put("request",request);
        object.put("keycloakToken",keycloakToken);
        return ResponseEntity.ok(object);
    }

    public Object softDeleteVolume(String s, String s1, String s2) {

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(s);
    }

    public Object fetchUserId(String s) {
        return null;
    }

    public Object userQuizStats(String s, String s1, LocalDate localDate, String s2) {
        return null;
    }

    public Object getprint(String s, Date date1, Date date2, String s1, boolean b, boolean b1) {
        return null;
    }
}
