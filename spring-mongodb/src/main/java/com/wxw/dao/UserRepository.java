package com.wxw.dao;

import com.wxw.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ：wxw.
 * @date ： 9:06 2020/12/22
 * @description： 下面一些方法是jpa持久化框架自带的
 * @link:
 * @version: v_0.0.1
 */
@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

    //查询操作，自己定义实现
    User getByName(String name);

    User getByNameLike(String name);


}
