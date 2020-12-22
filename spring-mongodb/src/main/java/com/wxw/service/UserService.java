package com.wxw.service;

import com.wxw.domain.User;

/**
 * @author ：wxw.
 * @date ： 9:02 2020/12/22
 * @description：业务层接口
 * @link: https://my.oschina.net/u/3194531/blog/4602375
 * @version: v_0.0.1
 */
public interface UserService {

    //增删改查接口
    public void saveUser(User user);

    public void removeUserByUserName(String name);

    public void updateUser(User user);

    public User getByUserName(String name);

    public User getByUserNameLike(String name);
}
