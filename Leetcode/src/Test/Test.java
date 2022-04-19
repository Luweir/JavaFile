package Test;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockTest {
    private Lock lock = new ReentrantLock();

    //需要参与同步的方法
    private void method(Thread thread) {
        lock.lock();
        try {
            System.out.println("线程名" + thread.getName() + "获得了锁");
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程名" + thread.getName() + "释放了锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        // 一个对象 锁他的属性
        LockTest lockTest = new LockTest();
        //线程1
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                lockTest.method(Thread.currentThread());
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                lockTest.method(Thread.currentThread());
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
//执行情况：线程名t1获得了锁
//         线程名t1释放了锁
//         线程名t2获得了锁
//         线程名t2释放了锁
