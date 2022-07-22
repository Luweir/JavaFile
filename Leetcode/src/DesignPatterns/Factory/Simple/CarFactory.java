package DesignPatterns.Factory.Simple;

public class CarFactory {
    public static Car getCar(String car) {
        if ("五菱宏光".equals(car)) {
            return new WuLing();
        }
        if ("特斯拉".equals(car)) {
            return new Tesla();
        }
        return null;
    }
}
