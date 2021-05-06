package com.wxw.services.impl;

import com.wxw.domain.RoleMenu;
import com.wxw.dao.RoleMenuMapper;
import com.wxw.services.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与权限关系表 服务实现类
 * </p>
 *
 * @author WXW
 * @since 2020-08-04
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

}
