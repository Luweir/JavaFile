package com.atguigu.test;

import java.lang.reflect.Method;

public class UserSevletTest {
    public void login() {
        System.out.println("这是login方法");
    }

    public void regist() {
        System.out.println("这是regist方法");
    }

    public void updateUser() {
        System.out.println("这是updateUser方法");
    }

    public void updatePassword() {
        System.out.println("这是updatePassword方法");
    }

    public static void main(String[] args) {
        String action = "updatePassword";
        try {
            //获取action业务鉴别字符串，获取相应的业务方法反射对象
            Method method = UserSevletTest.class.getDeclaredMethod(action);
            //调用目标业务方法
            method.invoke(new UserSevletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
