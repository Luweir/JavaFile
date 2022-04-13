package Necessary;

public class Simple {
    public static void main(String[] args) {
        xx.doSomething();
        xx.INSTANCE.doOtherThing();
        xx instance = xx.getInstance();
        xx instance1 = xx.getInstance();
        System.out.println(instance == instance1);
        System.out.println(xx.getInstance().age);
    }
    /**
     do something
     do other thing
     true
     */
}

// 饿汉式
class HungryMan {
    static private HungryMan instance = new HungryMan();

    private HungryMan() {
    }

    public static HungryMan getInstance() {
        return instance;
    }
}

// 懒汉式
class LazyMan {
    static private LazyMan instance;

    private LazyMan() {

    }

    public static LazyMan getInstance() {
        if (instance != null)
            instance = new LazyMan();
        return instance;
    }
}

// DCL懒汉式
class DCLLazyMan {
    static volatile private DCLLazyMan instance;

    private DCLLazyMan() {

    }

    public static DCLLazyMan getInstance() {
        if (instance == null) {
            synchronized (DCLLazyMan.class) {
                if (instance == null)
                    instance = new DCLLazyMan();
            }
        }
        return instance;
    }
}

// 静态内部类
class StaticClass {
    public static class simple {
        private static StaticClass instance = new StaticClass();
    }

    private StaticClass() {
    }

    public static StaticClass getInstance() {
        return simple.instance;
    }
}

// 枚举
enum xx {
    INSTANCE;

    public int age;

    private xx() {
        age = 10;
    }

    public static xx getInstance() {
        return INSTANCE;
    }

    public static void doSomething() {
        System.out.println("do something");
    }

    public void doOtherThing() {
        System.out.println("do other thing");
    }
}


