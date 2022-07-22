package com.atguigu.dao.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.pojo.User;

import java.sql.Connection;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername( String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ?";
        User user = queryForOne( sql, User.class, username);
        return user;
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username=? and password=?";
        return (User) queryForOne( sql, User.class, username, password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        return update( sql, user.getUsername(), user.getPassword(), user.getEmail());
    }
}
