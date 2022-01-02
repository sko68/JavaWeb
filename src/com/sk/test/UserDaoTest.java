package com.sk.test;

import com.sk.dao.UserDao;
import com.sk.dao.impl.UserDaoImpl;
import com.sk.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Description: TODO
 * @Author SK
 * @Email sk16168@163.com
 * @Date 2021/12/4 9:36
 * @Version 1.0
 */
public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();
    @Test
    public void testQueryUserByUsername() {

        if (userDao.queryUserByUsername("admin") == null) {
            System.out.println("用户名可用");
        } else {
            System.out.println("用户名已存在");

        }
    }

    @Test
    public void testQueryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("admin", "admin") == null) {
            System.out.println("用户名或密码错误");
        } else {
            System.out.println("登录成功");

        }

    }

    @Test
    public void testSaveUser() {
        System.out.println(userDao.saveUser(new User(null,"sk123","123456","sk123@168.com")));
    }

}