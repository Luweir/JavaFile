package DesignPatterns.Factory.Abstract1;

public class HuaWeiProduct implements IProductFactory {
    @Override
    public IphoneProduct iphoneProduct() {
        return new HuaWeiPhone();
    }

    @Override
    public IRouteProduct iRouteProduct() {
        return new HuaWeiRoute();
    }
}
