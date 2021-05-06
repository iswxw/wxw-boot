package com.wxw.services.impl;

import com.wxw.domain.User;
import com.wxw.dao.UserMapper;
import com.wxw.services.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-08-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
