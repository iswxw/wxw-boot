package com.wxw.services.impl;

import com.wxw.rpc.ProviderRPC;
import com.wxw.services.ConsumerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ Author ：wxw.
 * @ Date ： 20:02 2020/6/8
 * @ Description：消费者接口
 * @ Version:   v_0.0.1
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Resource
    private ProviderRPC providerRPC;

    @Override
    public String getName(){
        String setName = providerRPC.setName();
        System.out.println("从提供者方获得名称 = " + setName);
        return setName;
    }
}
