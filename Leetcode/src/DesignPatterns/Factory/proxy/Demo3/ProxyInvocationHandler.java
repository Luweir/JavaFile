package DesignPatterns.Factory.proxy.Demo3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 代理调用处理程序：自动生成代理类
public class ProxyInvocationHandler implements InvocationHandler {

    // 代理的的这件事 是什么就传什么  就是被代理的接口 这里是Rent
    private Object rent;

    public void setRent(Object rent) {
        this.rent = rent;
    }

    // 生成代理类
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(), this);
    }

    // 处理代理实例并返回结果
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 动态代理的本质就是反射
        seeHouse();
        Object invoke = method.invoke(rent, args);
        fare();
        return invoke;
    }

    public void seeHouse() {
        System.out.println("中介带我看房");
    }

    public void fare() {
        System.out.println("中介收我小费");
    }

}
