package HashTable;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code04_UnionFind {
    public static class Element<V> {
        public V value;

        public Element(V value) {
            this.value = value;
        }
    }

    public static class UnionFind<V> {
        // 值本身 -> 该值对应的包装对象
        public HashMap<V, Element> elementMap;
        // key 元素 -> value 该元素的父亲
        public HashMap<Element, Element> fatherMap;
        // key 某个集合的代表元素 -> 该集合的大小
        public HashMap<Element, Integer> sizeMap;

        public UnionFind(List<V> list) {
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            // 由list初始化UnionFind
            for (V value : list) {
                Element<V> element = new Element<V>(value);
                elementMap.put(value, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        // 找到元素element的代表元素返回，同时沿途中所有被查到的元素都直接指向代表元素 -> 扁平化
        private Element<V> findHead(Element<V> element) {
            Stack<Element<V>> stack = new Stack<>();
            // 找代表元素
            while (fatherMap.get(element) != element) {
                stack.push(element);
                element = fatherMap.get(element);
            }
            // 扁平化处理
            while (!stack.empty()) {
                fatherMap.put(stack.pop(), element);
            }
            return element;
        }

        // 查两个元素是否为同一个集合
        public boolean isSameSet(V a, V b) {
            if (findHead(elementMap.get(a)) == findHead(elementMap.get(b))) {
                return true;
            }
            return false;
        }

        // 合并两个元素所在集合
        public void union(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                Element<V> af = findHead(elementMap.get(a));
                Element<V> bf = findHead(elementMap.get(b));
                if (af != bf) {
                    Element<V> big = sizeMap.get(af) >= sizeMap.get(bf) ? af : bf;
                    Element<V> small = big == af ? bf : af;
                    fatherMap.put(small, big);
                    sizeMap.put(big, sizeMap.get(af) + sizeMap.get(bf));
                    sizeMap.remove(small);
                }
            }
        }
    }
}
