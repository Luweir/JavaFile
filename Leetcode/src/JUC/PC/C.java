package JUC.PC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// A执行完调用B B执行完调用C
public class C {
    public static void main(String[] args) {
        Data3 data = new Data3();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        }, "C").start();
    }
}

class Data3 {
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    //    Condition condition2 = lock.newCondition();
    //    Condition condition3 = lock.newCondition();
    private int number = 1; // 1A  2B  3C

    public void printA() {
        lock.lock();
        try {
            // 业务 执行 通知
            while (number != 1) {
                // 等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>AAAAAAAA");
            // 通知B
            number = 2;
            condition1.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            // 业务 执行 通知
            while (number != 2) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>BBBBBBBB");
            // 通知C
            number = 3;
            condition1.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            // 业务 执行 通知
            while (number != 3) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "=>CCCCCCCCCCCC");
            // 通知A
            number = 1;
            condition1.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}