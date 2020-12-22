package com.wxw.service.impl;

import com.wxw.dao.UserRepository;
import com.wxw.domain.User;
import com.wxw.service.UserService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ：wxw.
 * @date ： 9:03 2020/12/22
 * @description：
 * @link:
 * @version: v_0.0.1
 */
@Service
public class UserServiceImpl implements UserService {
    //由于我们添加了依赖，可以使用注解直接注入
    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private UserRepository userRepository;
    //保存用户
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
    //通过用户名字删除
    @Override
    public void removeUserByUserName(String name) {
        Query query=  new Query(Criteria.where("name").is(name));
        mongoTemplate.remove(query,User.class);
    }
    //通过用户id来更新名字
    @Override
    public void updateUser(User user) {
        Query q=new Query(new Criteria("id").is(user.getId()));
        Update update=new Update().set("name", user.getName());
        mongoTemplate.updateMulti(q, update, User.class);
    }
    //通过用户名查询
    @Override
    public User getByUserName(String name) {
        return userRepository.getByName(name);
    }
    //通过相似用户名查询
    @Override
    public User getByUserNameLike(String name) {
        return userRepository.getByNameLike(name);
    }
}
