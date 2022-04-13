package JUC;

public class Code01_SynchronizedMethod implements Runnable {
    static int i = 0;

    // synchronized 修饰实例方法
    public synchronized void add() {
        i++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++)
            add();
    }

    public static void main(String[] args) throws InterruptedException {
        Code01_SynchronizedMethod b = new Code01_SynchronizedMethod();
        Code01_SynchronizedMethod a = new Code01_SynchronizedMethod();
        Thread m1 = new Thread(a);
        Thread m2 = new Thread(b);
        m1.start();
        m2.start();
        // join是一个容器,它里面存放着线程a和线程b,他俩不死光,主线程不死. 他俩死,不管容器外面还有没有其他线程,主线程都死
        m1.join();
        m2.join();
        System.out.println(i);
    }
}
