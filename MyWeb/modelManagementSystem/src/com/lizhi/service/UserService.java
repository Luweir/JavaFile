package com.lizhi.service;


import com.lizhi.pojo.Download;
import com.lizhi.pojo.User;

import java.util.List;

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

    public User getUserByUsername(String username);

    /**
     * 更新用户信息
     *
     * @param user
     */
    public void updateUser(User user);

    public User getUserByUserId(int userId);

    public List<Download> getDownloadByUser(User user);

    public List<User> getAllUsers();

    public void deleteUser(int userId);
}
