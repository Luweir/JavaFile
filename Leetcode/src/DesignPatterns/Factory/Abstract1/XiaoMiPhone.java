package DesignPatterns.Factory.Abstract1;

public class XiaoMiPhone implements IphoneProduct{
    @Override
    public void start() {
        System.out.println("开启小米手机");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭小米手机");
    }

    @Override
    public void callUp() {
        System.out.println("小米手机打电话");
    }

    @Override
    public void sendMS() {
        System.out.println("小米手机发短信");
    }
}
