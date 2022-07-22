package DesignPatterns.Factory.Method;

public class MobaiFactory implements CarFactory{
    @Override
    public Car getCar() {
        return new MoBai();
    }
}
