package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import com.atguigu.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

public class UserDaoTest {
    @Test
    public void test1() {
        // 测试按用户名查找


        try {
            UserDaoImpl userDao = new UserDaoImpl();

            System.out.println(userDao.queryUserByUsername("admin"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        // 测试按用户名和密码查找，确认登陆
        try {
            UserDaoImpl userDao = new UserDaoImpl();

            if (userDao.queryUserByUsernameAndPassword("admin", "admin") == null) {
                System.out.println("用户名或密码错误");
            } else {
                System.out.println("登陆成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        // 测试按用户名和密码查找，确认登陆

        try {
            UserDaoImpl userDao = new UserDaoImpl();
            // 注意用户名唯一
            if (userDao.saveUser(new User(null, "root", "123456", "123123@qq.com")) == -1) {
                System.out.println("添加失败");
            } else {
                System.out.println("添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
