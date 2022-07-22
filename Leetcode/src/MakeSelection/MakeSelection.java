package MakeSelection;

import java.util.Random;

public class MakeSelection {
    public static void main(String[] args) {
        String[] selection = {"CBG 软件部", "CBG AI与智慧全场景业务部"};
        System.out.println(selection[new Random().nextInt(selection.length)]);
    }
}
