package com.wxw.service.impl;

import com.wxw.domain.ScheduleJob;
import com.wxw.dao.ScheduleJobMapper;
import com.wxw.service.IScheduleJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务 服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-12-09
 */
@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements IScheduleJobService {

}
