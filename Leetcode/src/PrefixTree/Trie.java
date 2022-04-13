package PrefixTree;

public class Trie {
    public TrieNode root; // root.end 表示加入了多少空串  root.pass 表示加入了多少字符串

    Trie() {
        root = new TrieNode();
    }

    // 插入节点
    public void insert(String word) {
        if (word == null)
            return;
        char[] chs = word.toCharArray();
        TrieNode node = root;
        node.pass++;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a'; // 根据字符决定走哪条路
            if (node.next[index] == null)
                node.next[index] = new TrieNode();
            node = node.next[index];
            node.pass++;
        }
        node.end++;
    }

    // 查询字符串word加入过几次
    public int search(String word) {
        if (word == null)
            return root.end;
        char[] chs = word.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a'; // 这个word只是别人的前缀，相当于isWord=false
            if (node.next[index] == null)
                return 0;
            node = node.next[index];
        }
        return node.end;
    }

    // 查询前缀prefix出现的次数 => 有几个字符串以prefix为前缀
    public int prefixNumber(String prefix) {
        if (prefix == null) {
            return 0;
        }
        char[] chs = prefix.toCharArray();
        TrieNode node = root;
        int index = 0;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.next[index] == null)
                return 0;
            node = node.next[index];
        }
        return node.pass;
    }

    // 删除字符串word
    public void delete(String word) {
        // 如果存在该word
        if (search(word) != 0) {
            char[] chars = word.toCharArray();
            TrieNode node = root;
            node.pass--;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                node = node.next[index];
                if (--node.pass == 0) {
                    node = null; // java能这么写，因为置null后 某块内存没人指向 JVM最后会统一释放，而C++需要遍历到底去析构
                    return;
                }
            }
            node.end--;

        }
    }
}
