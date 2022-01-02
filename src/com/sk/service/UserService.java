package com.sk.service;

import com.sk.pojo.User;

/**
 * @Description: TODO
 * @Author SK
 * @Email sk16168@163.com
 * @Date 2021/12/4 10:25
 * @Version 1.0
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登录
     * @param user
     * @return 如果返回null，说明登录失败，返回有值，登录成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名已存在，否则用户名可以用
     */
    public boolean existUserName(String username);



}
