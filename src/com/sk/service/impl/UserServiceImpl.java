package com.sk.service.impl;

import com.sk.dao.UserDao;
import com.sk.dao.impl.UserDaoImpl;
import com.sk.pojo.User;
import com.sk.service.UserService;

/**
 * @Description: TODO
 * @Author SK
 * @Email sk16168@163.com
 * @Date 2021/12/4 10:31
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);

    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());


    }

    @Override
    public boolean existUserName(String username) {

        if (userDao.queryUserByUsername(username) == null) {
            return false;
        }
        return true;
    }
}
