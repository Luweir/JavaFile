package DesignPatterns.Factory.Simple;

public class Consumer {
    public static void main(String[] args) {
        // 传统方式：需要了解接口  了解实现类
//        Car car = new WuLing();
//        Car car1 = new Tesla();
        // 使用工厂  不用关心里面细节   比如构造器参数这些
        Car car = CarFactory.getCar("五菱宏光");
        Car car1 = CarFactory.getCar("特斯拉");

        car.name();
        car1.name();
    }
}
