package Greedy;

import java.util.Arrays;
import java.util.Comparator;

public class Code01_BestArrange {
    public static class Program {
        public int start;
        public int end;

        Program(int s, int e) {
            start = s;
            end = e;
        }
    }

    // 比较器
    public static class ProgramComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static int bestArrange(Program[] programs, int timePoint) {
        Arrays.sort(programs, new ProgramComparator());
        int result = 0;
        // 从左向右遍历所有会议
        for (int i = 0; i < programs.length; i++) {
            if (timePoint <= programs[i].start) {
                result++;
                timePoint = programs[i].end;
            }
        }
        return result;
    }
}
