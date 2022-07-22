package DesignPatterns.Factory.proxy.Demo1;

public class Client {
    public static void main(String[] args) {
        // 房东
        Host host = new Host();
        // 代理 中介帮房东出租房子  但是呢一般还会有附属操作
        Proxy proxy = new Proxy(host);
        // 找中介，不用面对房东，只接触中介
        proxy.rent();
    }
}
