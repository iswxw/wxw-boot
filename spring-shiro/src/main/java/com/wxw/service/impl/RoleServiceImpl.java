package com.wxw.service.impl;

import com.wxw.domain.Role;
import com.wxw.dao.RoleMapper;
import com.wxw.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-08-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
