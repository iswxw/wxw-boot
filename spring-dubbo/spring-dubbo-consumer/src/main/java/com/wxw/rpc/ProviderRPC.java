package com.wxw.rpc;

import com.wxw.services.ProviderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @ Author ：wxw.
 * @ Date ： 12:46 2020/6/9
 * @ Description：远程调用提供的接口
 * @ Version:   v_0.0.1
 */
@Component
public class ProviderRPC {

    @DubboReference
    private ProviderService providerService;

    /**
     * 发布服务信息
     * @return
     */
    public String setName(){
        return providerService.setName();
    }
}
