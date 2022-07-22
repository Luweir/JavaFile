package JUC.UnsafeCollections;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
    public static void main(String[] args) {
        // 并发下 ArrayList 不安全吗？ Synchronized
        /**
         * 解决方案：
         * 1、List<String> list = new Vector<>();  vector比较老，且
         * 2、List<String> list= Collections.synchronizedList(new ArrayList<>());
         * 3、List<String> list=new CopyOnWriteArrayList<>();
         * CopyOnWrite 写时复制   COW 计算机程序设计领域的一种优化策略
         * 多个线程调用的时候 读写分离  写的时候copy一个空间，写完再覆盖原数据；
         */

        List<String> list=new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
