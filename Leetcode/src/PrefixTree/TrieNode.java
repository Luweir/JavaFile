package PrefixTree;

public class TrieNode {
    public int pass; // 通过该前缀的次数 => 以该字符串为前缀的字符串的个数
    public int end; // 该字符串出现的次数
    public TrieNode[] next; // next[0]==null 没有走向'a'的路    next[1]!=null 有走向'b'的路
                            // 如果字符很多，就不适合开辟数组，用Hash表  HashMap<Char,Node> next 表示通过该字符走到下一个哪个节点！！！

    TrieNode() {
        pass = 0;
        end = 0;
        next = new TrieNode[26];
    }
}
