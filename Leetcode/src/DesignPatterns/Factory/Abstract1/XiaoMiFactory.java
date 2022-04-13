package DesignPatterns.Factory.Abstract1;

public class XiaoMiFactory implements IProductFactory {
    @Override
    public IphoneProduct iphoneProduct() {
        return new XiaoMiPhone();
    }

    @Override
    public IRouteProduct iRouteProduct() {
        return new XiaoMiRoute();
    }
}
