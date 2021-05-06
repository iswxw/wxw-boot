package com.wxw.controller;

import com.wxw.domain.User;
import com.wxw.services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ：wxw.
 * @date ： 9:09 2020/12/22
 * @description：用户控制层
 * @link:
 * @version: v_0.0.1
 */
@RestController
public class UserController {
    //注入service
    @Resource
    private UserService userService;

    //多增加几个用户
    @PostMapping("/save")
    public String saveUser() {
        for(int i=0;i<3;i++){
            User user=new User();
            user.setId(i);
            user.setName("fdd"+i);
            user.setPassword("12345"+i);
            userService.saveUser(user);
        }
        return "插入用户成功";
    }
    //删除fdd0
    @GetMapping("/remove")
    @ResponseBody
    public String removeUserByUserName() {
        userService.removeUserByUserName("fdd0");
        return "删除用户成功";
    }
    //把1号名字改一下
    @PostMapping("/update")
    @ResponseBody
    public String updateUser() {
        User user=new User();
        user.setId(1);
        user.setName("java的架构师技术栈");
        user.setPassword("123456");
        userService.updateUser(user);
        return "更新用户信息成功";
    }
    //通过名字查询
    @GetMapping("/getUserByName")
    @ResponseBody
    public User getUserByName() {
        User user=userService.getByUserName("fdd2");
        return user;
    }
    //通过相似名字查询
    @GetMapping("/getUserByNameLike")
    @ResponseBody
    public User getUserByNameLike() {
        User user=userService.getByUserNameLike("fdd");
        return user;
    }
}