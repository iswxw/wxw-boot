package com.wxw.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxw.controller.RestDemoController;
import com.wxw.service.RestDemoService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author weixiaowei
 * @desc: rest 风格 mock 单元测试
 * @date: 2021/4/28
 */
public class ControllerTest {

    private static final String AUTHORIZATION = "Authorization";

    private MockMvc mvc;

    @Mock
    //要mock被测类中依赖的对象使用@Mock注解
    private RestDemoService restDemoService;

    @InjectMocks
    //被测类本身使用@InjectMocks注解
    private RestDemoController demoController;


    @Test
    public void testSoftDeleteVolume() throws Exception {
        //mock方法行为
        Mockito.when(restDemoService.softDeleteVolume("1", "2", "3"))
                .thenReturn(ResponseEntity.status(HttpStatus.ACCEPTED).build());

        //模拟接口调用
        this.mvc.perform(delete("/ebs/v1/volumes/1/soft")
                .header(AUTHORIZATION, "2").content("3"))
                .andExpect(status().isOk())
                //对接口响应进行验证
                .andExpect(content().json("{\"code\":\"202\",\"message\":null,\"data\":null}"));
    }


    @Test
    public void attach() throws Exception {
        Mockito.when(restDemoService.attachVolume(Mockito.any(), Mockito.any(), Mockito.anyString()))
                .thenReturn(ResponseEntity.ok(new JSONObject()));

        mvc.perform(post("/ebs/v1/volumes/123/action/attach")
                .header(AUTHORIZATION, "123")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"os-attach\":{\"instance_uuid\":\"123\"}}"))
                .andExpect(status().isOk());
    }

    @Before
    public void setup() {
        //初始化
        MockitoAnnotations.initMocks(this);
        //构建mvc环境
        mvc = MockMvcBuilders.standaloneSetup(demoController).build();
    }


}
