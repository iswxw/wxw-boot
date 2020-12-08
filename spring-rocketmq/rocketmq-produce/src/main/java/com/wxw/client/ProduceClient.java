package com.wxw.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ：wxw.
 * @date ： 11:45 2020/12/4
 * @description：商品服务客户端
 * @link: name = "product-service"是你调用服务端名称
 * @version: v_0.0.1
 */
//@FeignClient(name = "product-service")
public interface ProduceClient {

    /**
     * @Title:
     * @Description: 这样组合就相当于http://product-service/api/v1/product/find
     * @author xub
     * @throws
     */
    @GetMapping("/api/v1/produce/find")
    String findById(@RequestParam(value = "produceId") int produceId);

}
