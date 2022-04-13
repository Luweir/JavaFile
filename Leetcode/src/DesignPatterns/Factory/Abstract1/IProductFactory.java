package DesignPatterns.Factory.Abstract1;

// 抽象产品工厂
public interface IProductFactory {
    // 生成手机
    IphoneProduct iphoneProduct();
    // 生成路由器
    IRouteProduct iRouteProduct();
}
