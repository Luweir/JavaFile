package Necessary;

import java.util.concurrent.TimeUnit;

public class DieadLock {
    public static String lockA = "lockA";
    public static String lockB = "lockB";

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (DieadLock.lockA) {
                System.out.println(Thread.currentThread().getName() + "get " + DieadLock.lockA + ", want " + DieadLock.lockB);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (DieadLock.lockB) {
                    System.out.println(Thread.currentThread().getName() + "get " + DieadLock.lockB + ", want " + DieadLock.lockA);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (DieadLock.lockB) {
                System.out.println(Thread.currentThread().getName() + "get " + DieadLock.lockB + ", want " + DieadLock.lockA);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (DieadLock.lockA) {
                    System.out.println(Thread.currentThread().getName() + "get " + DieadLock.lockA + ", want " + DieadLock.lockA);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
