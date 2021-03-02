package com.wxw.service;

import com.wxw.domain.Engines;

import java.util.List;

/**
 * @author ：wxw.
 * @date ： 15:12 2021/3/2
 * @description：
 * @link:
 * @version: v_0.0.1
 */
public interface EnginesService {

    /**
     * 查询引擎集合
     * @return
     */
    List<Engines> queryEngineList();
}
