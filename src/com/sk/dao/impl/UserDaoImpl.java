package com.sk.dao.impl;

import com.sk.dao.UserDao;
import com.sk.pojo.User;

/**
 * @Description: TODO
 * @Author SK
 * @Email sk16168@163.com
 * @Date 2021/12/4 9:18
 * @Version 1.0
 */
public class UserDaoImpl extends BaseDao implements UserDao {


    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where `username` = ?;";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) value(?,?,?);";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select id, username,password,email from t_user where username = ? and password =?;";
        return queryForOne(User.class,sql,username,password);
    }



}
