package DesignPatterns.Factory.Method;

import DesignPatterns.Factory.Simple.CarFactory;

public class Consumer {
    public static void main(String[] args) {
        Car car = new WuLingFactory().getCar();
        Car car1 = new TeslaFactory().getCar();
        Car car2 = new MobaiFactory().getCar();
        car.name();
        car1.name();
        car2.name();
    }
}
