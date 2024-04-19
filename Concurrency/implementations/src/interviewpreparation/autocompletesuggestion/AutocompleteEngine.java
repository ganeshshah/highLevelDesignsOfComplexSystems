package interviewpreparation.autocompletesuggestion;

import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isWord;

    TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }
}

public class AutocompleteEngine {
    private TrieNode root;

    AutocompleteEngine() {
        root = new TrieNode();
    }

    // Inserts a word into the trie
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isWord = true;
    }

    // Returns all words in the trie that start with the given prefix
    public List<String> autocomplete(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode node = root;
        // Traverse the trie to the node representing the prefix
        for (char ch : prefix.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return result; // Prefix not found
            }
            node = node.children.get(ch);
        }
        // Collect all words with the given prefix
        collectWords(prefix, node, result);
        return result;
    }

    private void collectWords(String prefix, TrieNode node, List<String> result) {
        if (node.isWord) {
            result.add(prefix);
        }
        for (char ch : node.children.keySet()) {
            collectWords(prefix + ch, node.children.get(ch), result);
        }
    }

    public static void main(String[] args) {
        AutocompleteEngine engine = new AutocompleteEngine();
        engine.insert("apple");
        engine.insert("app");
        engine.insert("application");
        engine.insert("banana");
        engine.insert("bat");

        System.out.println("Autocomplete for 'app': " + engine.autocomplete("app"));
        System.out.println("Autocomplete for 'ba': " + engine.autocomplete("ba"));
        System.out.println("Autocomplete for 'foo': " + engine.autocomplete("foo"));
    }
}
