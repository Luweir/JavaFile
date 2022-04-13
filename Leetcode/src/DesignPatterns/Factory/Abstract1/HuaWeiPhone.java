package DesignPatterns.Factory.Abstract1;

public class HuaWeiPhone implements IphoneProduct{
    @Override
    public void start() {
        System.out.println("华为手机开机");
    }

    @Override
    public void shutdown() {
        System.out.println("华为手机关机");
    }

    @Override
    public void callUp() {
        System.out.println("华为手机打电话");
    }

    @Override
    public void sendMS() {
        System.out.println("华为手机发信息");
    }
}
