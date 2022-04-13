package Necessary;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFind<V> {
    public HashMap<V, Element> elementHashMap;
    public HashMap<Element, Element> fatherMap;
    public HashMap<Element, Integer> sizeMap;

    // 初始化
    public UnionFind(List<V> list) {
        elementHashMap = new HashMap<>();
        fatherMap = new HashMap<>();
        sizeMap = new HashMap<>();
        for (V v : list) {
            Element<V> element = new Element<>(v);
            elementHashMap.put(v, element);
            fatherMap.put(element, element);
            sizeMap.put(element, 1);
        }
    }

    // 找father
    public Element<V> findHead(Element<V> element) {
        Stack<Element<V>> stack = new Stack<>();
        while (fatherMap.get(element) != element) {
            stack.add(element);
            element = fatherMap.get(element);
        }
        while (!stack.isEmpty()) {
            fatherMap.put(stack.pop(), element);
        }
        return element;
    }

    // a和b是否一个集合
    public boolean isSameSet(V a, V b) {
        if (findHead(elementHashMap.get(a)) == findHead(elementHashMap.get(b)))
            return true;
        return false;
    }

    public void union(V a, V b) {
        if (elementHashMap.containsKey(a) && elementHashMap.containsKey(b)) {
            Element<V> fatherA = findHead(elementHashMap.get(a));
            Element<V> fatherB = findHead(elementHashMap.get(b));
            if (fatherA != fatherB) {
                Element<V> big = sizeMap.get(fatherA) > sizeMap.get(fatherB) ? fatherA : fatherB;
                Element<V> small = big == fatherA ? fatherB : fatherA;
                fatherMap.put(small, big);
                sizeMap.put(big, sizeMap.get(big) + sizeMap.get(small));
                sizeMap.remove(small);
            }
        }
    }
}


class Element<V> {
    V value;

    Element(V value) {
        this.value = value;
    }
}
