package com.atguigu.dao;

import com.atguigu.pojo.User;

import java.sql.Connection;

public interface UserDao {

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户
     *
     * @param connection
     * @param username
     * @param password
     * @return
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 保存用户信息
     *
     * @param connection
     * @param user
     * @return
     */
    public int saveUser(User user);

}
