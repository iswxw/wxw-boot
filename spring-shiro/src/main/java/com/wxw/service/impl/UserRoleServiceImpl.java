package com.wxw.service.impl;

import com.wxw.domain.UserRole;
import com.wxw.dao.UserRoleMapper;
import com.wxw.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色关系表 服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-08-04
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
