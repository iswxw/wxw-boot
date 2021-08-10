package com.wxw.manage.spi;


import com.wxw.services.DubboSpiService;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @contract: 公众号：Java半颗糖
 * @desc: Dubbo spi 机制
 * @link: https://aobing.blog.csdn.net/article/details/108256452
 */
public class DemoDubboSpi {
    public static void main(String[] args) {
       // Java_spi();
        Dubbo_spi();
    }

    private static void Dubbo_spi() {
        ExtensionLoader<DubboSpiService> extensionLoader = ExtensionLoader.getExtensionLoader(DubboSpiService.class);
        DubboSpiService oneDubboSpiServiceImpl = extensionLoader.getExtension("oneDubboSpiServiceImpl");
        oneDubboSpiServiceImpl.say();
    }

    private static void Java_spi() {
        ServiceLoader<DubboSpiService> serviceLoader  =  ServiceLoader.load(DubboSpiService.class);
        Iterator<DubboSpiService> iterator = serviceLoader.iterator();
        System.out.println("加载到的类");
        while (iterator.hasNext()){
            DubboSpiService spiService = iterator.next();
            spiService.say();
        }
        System.out.println("加载完毕");
    }
}
