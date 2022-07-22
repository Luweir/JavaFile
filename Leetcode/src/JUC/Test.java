package JUC;

import java.util.concurrent.ArrayBlockingQueue;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        test2();
    }

    // 抛出异常
    public static void test1() {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        // 添加元素
        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));

        // System.out.println(arrayBlockingQueue.add("d"));// 抛出异常
        // Exception in thread "main" java.lang.IllegalStateException: Queue full

        // 弹出元素
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        // System.out.println(arrayBlockingQueue.remove()); 抛出异常
        // Exception in thread "main" java.util.NoSuchElementException
    }

    public static void test2() {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);
        // 添加元素
        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));

        System.out.println(arrayBlockingQueue.offer("d"));// 不抛出异常 false


        // 弹出元素
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
         System.out.println(arrayBlockingQueue.poll()); // 不抛出异常  null
    }
}

