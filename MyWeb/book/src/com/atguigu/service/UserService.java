package com.atguigu.service;

import com.atguigu.pojo.User;

// 业务层
public interface UserService {
    /**
     * 注册用户
     *
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登陆
     *
     * @param user
     */
    public User login(User user);

    /**
     * 检查用户名是否已存在
     *
     * @param username
     * @return
     */
    public boolean existUsername(String username);

}
