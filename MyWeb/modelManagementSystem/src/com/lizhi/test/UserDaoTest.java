package com.lizhi.test;

import com.lizhi.dao.impl.UserDaoImpl;
import com.lizhi.pojo.User;
import com.lizhi.service.UserService;
import com.lizhi.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDaoImpl userDao=new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        String username="admin";
        User user=userDao.queryUserByUsername(username);
        System.out.println(user);
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        String username="admin";
        String password="admin";
        User user=userDao.queryUserByUsernameAndPassword(username,password);
        System.out.println(user);
    }

    @Test
    public void saveUser() {
        User user=new User(null, "test1", "12345566", null, null, null, null, "123123123@163.com");
        int count = userDao.saveUser(user);
        System.out.println(count);
    }
}