package com.wxw.service.impl;

import com.wxw.dao.EnginesMapper;
import com.wxw.domain.Engines;
import com.wxw.domain.EnginesExample;
import com.wxw.service.EnginesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：wxw.
 * @date ： 15:12 2021/3/2
 * @description：
 * @link:
 * @version: v_0.0.1
 */
@Service
public class EnginesServiceImpl implements EnginesService {

    @Resource
    private EnginesMapper enginesMapper;

    @Override
    public List<Engines> queryEngineList() {
        EnginesExample example = new EnginesExample();
        return enginesMapper.selectByExample(example);
    }
}
