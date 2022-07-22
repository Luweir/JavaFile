package DesignPatterns.Factory.Builder;

// 抽象的建造者  方法
public abstract class Builder {
    abstract void buildA();

    abstract void buildB();

    abstract void buildC();

    abstract void buildD();

    abstract Product getProduct();
}
