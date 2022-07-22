package DesignPatterns.Factory.proxy.Demo4;

import DesignPatterns.Factory.proxy.Demo2.UserService;
import DesignPatterns.Factory.proxy.Demo2.UserServiceImpl;

public class Client {
    public static void main(String[] args) {
        // 真实对象
        UserService userService = new UserServiceImpl();

        // 代理角色 不存在
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        proxyInvocationHandler.setRent(userService);// 设置要代理的事件

        // 动态生成代理类
        UserService proxy = (UserService) proxyInvocationHandler.getProxy();
        proxy.delete();
    }
}
