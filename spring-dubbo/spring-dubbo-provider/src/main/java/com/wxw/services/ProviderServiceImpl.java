package com.wxw.services;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

/**
 * @ Author ：wxw.
 * @ Date ： 19:55 2020/6/8
 * @ Description：提供者测试
 * @ Version:   v_0.0.1
 */
@Component
@DubboService  //服务发布出去
public class ProviderServiceImpl implements ProviderService {

    @Override
    public String setName() {

        return "provider: hi,consumer 我把消息发布出来啦";
    }
}
