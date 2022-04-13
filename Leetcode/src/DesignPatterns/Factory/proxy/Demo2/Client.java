package DesignPatterns.Factory.proxy.Demo2;

public class Client {
    public static void main(String[] args) {
        // 真实角色：只实现了增删改查
        UserServiceImpl userService = new UserServiceImpl();

        // 使用代理完成已有功能，扩展新的公共功能
        UserServiceProxy userServiceProxy = new UserServiceProxy();
        userServiceProxy.setUserService(userService);

        userServiceProxy.add();
    }

}
