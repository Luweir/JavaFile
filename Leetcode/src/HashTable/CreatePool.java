package HashTable;

import java.beans.JavaBean;
import java.util.HashMap;

public class CreatePool {
    static public class Pool {
        HashMap<String, Integer> keyMapValue;
        HashMap<Integer, String> valueMapKey;
        int size;

        Pool() {
            keyMapValue = new HashMap<>();
            valueMapKey = new HashMap<>();
            size = 0;
        }

        public void insert(String s) {
            keyMapValue.put(s, size);
            valueMapKey.put(size, s);
            size++;
        }

        public String getRandom() {
            if (size == 0)
                return null;
            int randomIndex = (int) (Math.random() * size);// 返回0~size-1之间的随机数
            return valueMapKey.get(randomIndex);
        }

        public void delete(String s) {
            if (keyMapValue.containsKey(s)) {
                int deleteIndex = keyMapValue.get(s);
                String lastString = valueMapKey.get(--size);
                keyMapValue.remove(s);
                valueMapKey.remove(deleteIndex);
                keyMapValue.put(lastString, deleteIndex);
                valueMapKey.put(deleteIndex, lastString);
            }
        }
    }
}
