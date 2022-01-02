package com.sk.test;

import com.sk.pojo.User;
import com.sk.service.UserService;
import com.sk.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Description: TODO
 * @Author SK
 * @Email sk16168@163.com
 * @Date 2021/12/4 10:38
 * @Version 1.0
 */
public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"bbj","666","bbj@123.com"));
        userService.registerUser(new User(null, "sk168", "5555", "sk168@168.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"sk168","5555",null)));
    }

    @Test
    public void existUserName() {
        if (userService.existUserName("sk168")) {
            System.out.println("用户名已存在");
        } else {
            System.out.println("用户名可用！");
        }
    }
}