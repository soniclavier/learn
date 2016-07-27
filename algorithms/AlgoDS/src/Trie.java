import java.util.HashMap;
import java.util.Map;

/**
 * Created by vishnu on 6/9/16.
 */
public class Trie {

    public static void main(String[] args) {
        TrieNode tn = new TrieNode();
        tn.insert("abc");
        tn.insert("abdfe");
        System.out.println(tn.search("abdfe",false));
        System.out.println(tn.search("abde",true));
    }
}


class TrieNode {
    Map<Character,TrieNode> elems;
    boolean end = false;

    public TrieNode() {
        elems = new HashMap<>();
    }

    public void insert(String str) {
        if (str == null || str.trim().length() ==0) return;
        char ch = str.charAt(0);
        TrieNode tr = null;
        if (elems.containsKey(ch)) {
            tr = elems.get(ch);
            tr.insert(str.substring(1));
            if (str.length() == 1) tr.end = true;
        } else {
            tr = new TrieNode();
            if (str.length() == 1) tr.end = true;
            elems.put(ch,tr);
            tr.insert(str.substring(1));
        }
    }

    public boolean search(String str, boolean prefix) {
        if (str.trim().equals("")) {
            if (prefix)
                return true;
            else
                return end;
        }
        char ch = str.charAt(0);
        if (elems.containsKey(ch)) {
            return elems.get(ch).search(str.substring(1),prefix);
        } else
            return false;
    }


    

}
