package DesignPatterns.Factory.Builder;

public class Consumer {
    public static void main(String[] args) {
        // 指挥
        Director director = new Director();
        // 指挥具体的工人 完成 得到产品 构建与表示分离
        Product build = director.build(new Worker());
        System.out.println(build.toString());

    }
}
