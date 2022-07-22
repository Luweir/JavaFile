package DesignPatterns.Factory.Builder;

// 指挥 如何构建一个工程
public class Director {
    // 指挥工人按照顺序建房子
    public Product build(Builder builder) {
        builder.buildA();
        builder.buildB();
        builder.buildC();
        builder.buildD();
        return builder.getProduct();
    }
}
