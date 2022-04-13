package BinaryTree;

import java.util.List;

public class Code13_TreeDP_HappyParty {
    public static class Employee {
        public int happy;
        List<Employee> next;

        public Employee(int h) {
            happy = h;
        }
    }

    public static class Info {
        public int laiMaxHappy;
        public int buMaxHappy;

        public Info(int l, int b) {
            laiMaxHappy = l;
            buMaxHappy = b;
        }
    }

    public static Info process(Employee head) {
        // base case
        if (head.next == null) {
            return new Info(head.happy, 0);
        }

        // 可能性分类
        int lai = head.happy;
        int bu = 0;
        for (Employee e : head.next) {
            Info nextInfo = process(e);
            lai += nextInfo.buMaxHappy; // head来，next就不能来
            bu += Math.max(nextInfo.laiMaxHappy, nextInfo.buMaxHappy); // head来，next可来可不来，取最大值
        }
        return new Info(lai, bu);
    }
}
