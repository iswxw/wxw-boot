package com.wxw.services.impl;

import com.wxw.services.DubboSpiService;

/**
 * @contract: 公众号：Java半颗糖
 * @desc:
 * @link:
 * @company:
 */
public class OneDubboSpiServiceImpl implements DubboSpiService {
    @Override
    public void say() {
        System.out.println("插件一 进行加载！！");
    }
}
