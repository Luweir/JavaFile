package UnderstandOOP;

import java.util.Comparator;
import java.util.PriorityQueue;

public class UnderstandInterface {
    public static void main(String[] args) {
        Computer computer = new Computer();
        Printer printer = new Printer();
        computer.transferData(printer);
        // 多态
        USB flash = new Flash();
        computer.transferData(flash);
    }
}

class Computer {
    public void transferData(USB usb) {
        usb.start();

        System.out.println("传输数据");

        usb.start();
    }
}

// USB接口
interface USB {
    // 定义常量属性

    // 定义方法
    void start();

    void stop();
}

// U盘实现了USB接口
class Flash implements USB {

    @Override
    public void start() {
        System.out.println("U盘开启工作");
    }

    @Override
    public void stop() {
        System.out.println("U盘结束工作");
    }
}

// 打印机实现了USB接口
class Printer implements USB {

    @Override
    public void start() {
        System.out.println("打印机开始工作");
    }

    @Override
    public void stop() {
        System.out.println("打印机结束工作");
    }
}