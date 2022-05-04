package Test;


import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Solution {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new MyThread(lockA, lockB)).start();
        new Thread(new MyThread(lockB, lockA)).start();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        Thread checkDeadLock = new Thread(() -> {
            long[] deadlockedThreads = threadMXBean.findDeadlockedThreads(); // 返回出现死锁的线程ID
            if (deadlockedThreads != null) {
                ThreadInfo[] threadInfo = threadMXBean.getThreadInfo(deadlockedThreads); // 获得死锁的线程信息
                System.out.println("检测到的死锁信息");
                for (int i = 0; i < threadInfo.length; i++) {
                    System.out.println(threadInfo[i].getThreadId() + "-----" + threadInfo[i].getThreadName());
                }
            }
        });
        // 创建定时任务线程池
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(checkDeadLock, 5, 5, TimeUnit.SECONDS); // checkDeadLock每5s监测一次 监测5次
    }

}

class MyThread implements Runnable {
    private String lockGet;
    private String lockWant;

    public MyThread(String lockGet, String lockWant) {
        this.lockGet = lockGet;
        this.lockWant = lockWant;
    }

    @Override
    public void run() {
        synchronized (lockGet) {
            System.out.println(Thread.currentThread().getName() + "get " + lockGet + ", want " + lockWant);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockWant) {
                System.out.println(Thread.currentThread().getName() + "get " + lockWant + ", want " + lockGet);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
/**
 * Thread-0get lockA, want lockB
 * Thread-1get lockB, want lockA
 * 检测到的死锁信息
 * 14-----Thread-0
 * 15-----Thread-1
 * 检测到的死锁信息
 * 14-----Thread-0
 * 15-----Thread-1
 */

