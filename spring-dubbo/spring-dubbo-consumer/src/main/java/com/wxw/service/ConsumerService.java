package com.wxw.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @ Author ：wxw.
 * @ Date ： 20:02 2020/6/8
 * @ Description：消费者接口
 * @ Version:   v_0.0.1
 */
@Service
public class ConsumerService {

    @DubboReference
    ProviderService providerService;

    public void getName(){
        String setName = providerService.setName();
        System.out.println("从提供者方获得名称 = " + setName);
    }
}
