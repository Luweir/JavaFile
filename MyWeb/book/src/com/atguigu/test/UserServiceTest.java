package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null, "yll", "123213", "123123213@qq.com"));
        userService.registerUser(new User(null, "lyw", "1wqdsds", "1qwsdax3@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "yll", "123213", null)));
    }

    @Test
    public void existUsername() {
        if (userService.existUsername("lyw")) {
            System.out.println("用户名已存在");
        } else {
            System.out.println("用户名可用");
        }
    }
}