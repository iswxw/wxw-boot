package com.wxw.services;

import org.apache.dubbo.common.extension.SPI;
import org.apache.dubbo.rpc.cluster.support.FailoverCluster;

/**
 * @contract: 公众号：Java半颗糖
 * @desc:
 * @link:
 * @company:
 */
@SPI(FailoverCluster.NAME)
public interface DubboSpiService {

    void say();
}
