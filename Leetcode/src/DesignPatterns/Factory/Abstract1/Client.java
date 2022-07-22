package DesignPatterns.Factory.Abstract1;

public class Client {
    public static void main(String[] args) {
        System.out.println("================小米产品================");
        XiaoMiFactory xiaoMiFactory=new XiaoMiFactory();
        IphoneProduct iphoneProduct = xiaoMiFactory.iphoneProduct();
        iphoneProduct.start();
        iphoneProduct.callUp();
        IRouteProduct iRouteProduct = xiaoMiFactory.iRouteProduct();
        iRouteProduct.start();
        iRouteProduct.openWifi();
        System.out.println("===============华为产品================");
        HuaWeiProduct huaWeiProduct = new HuaWeiProduct();
        IphoneProduct iphoneProduct1 = huaWeiProduct.iphoneProduct();
        iphoneProduct1.start();
    }
}
