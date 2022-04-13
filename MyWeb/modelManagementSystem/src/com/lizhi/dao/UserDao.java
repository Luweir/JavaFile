package com.lizhi.dao;


import com.lizhi.pojo.User;

import java.util.List;

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
     * @param username
     * @param password
     * @return
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 保存用户信息
     *
     * @param user
     * @return
     */
    public int saveUser(User user);

    public void updateUser(User user);

    public User queryUserByUserId(int userId);

    public List<User> queryAllUsers();

    public void deleteByUserId(int userId);
}
