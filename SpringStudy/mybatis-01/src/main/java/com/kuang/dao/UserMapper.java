package com.kuang.dao;

import com.kuang.pojo.User;

import java.util.HashMap;
import java.util.List;

public interface UserMapper {
    List<User> getUserList();

    User getUserById(int id);

    User getUserById2(HashMap<String, Object> map);

    int addUser(User user);
}
