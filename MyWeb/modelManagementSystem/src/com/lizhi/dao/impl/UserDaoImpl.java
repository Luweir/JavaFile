package com.lizhi.dao.impl;

import com.lizhi.dao.UserDao;
import com.lizhi.pojo.User;

import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`permissions`,`department`,`realName`,`telephone`,`email` from t_user where `username` = ?";
        User user = queryForOne(sql, User.class, username);
        return user;
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`permissions`,`department`,`realName`,`telephone`,`email` from t_user where username=? and password=?";
        return queryForOne(sql, User.class, username, password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`permissions`,`email`) values(?,?,?,?)";
        return update(sql, user.getUsername(), user.getPassword(), 1, user.getEmail());
    }

    @Override
    public void updateUser(User user) {
        String sql = "update t_user set `id`=?,`username`=?,`password`=?,`permissions`=?,`department`=?,`realName`=?,`telephone`=?,`email`=? where `id`=?";
        update(sql, user.getId(), user.getUsername(), user.getPassword(), user.getPermissions(), user.getDepartment(), user.getRealName(), user.getTelephone(), user.getEmail(), user.getId());
    }

    @Override
    public User queryUserByUserId(int userId) {
        String sql = "select `id`,`username`,`password`,`permissions`,`department`,`realName`,`telephone`,`email` from t_user where `id`=?";
        return queryForOne(sql, User.class, userId);
    }

    @Override
    public List<User> queryAllUsers() {
        String sql = "select `id`,`username`,`password`,`permissions`,`department`,`realName`,`telephone`,`email` from t_user";
        return queryForList(sql, User.class);
    }

    @Override
    public void deleteByUserId(int userId) {
        String sql = "delete from t_user where `id`=?";
        update(sql, userId);
    }
}
