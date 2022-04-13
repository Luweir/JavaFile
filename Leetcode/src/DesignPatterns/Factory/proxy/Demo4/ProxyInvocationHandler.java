package DesignPatterns.Factory.proxy.Demo4;

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
        log(method.getName());
        Object invoke = method.invoke(rent, args);
        return invoke;
    }

    // 如果需要增加日志功能
    public void log(String str) {
        System.out.println("执行了" + str + "方法");
    }
}
