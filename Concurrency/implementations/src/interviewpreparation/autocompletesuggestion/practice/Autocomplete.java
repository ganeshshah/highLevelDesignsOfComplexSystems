package interviewpreparation.autocompletesuggestion.practice;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node{
    Map<Character,Node> children;
    boolean isWord;

    Node(){
        children = new HashMap<>();
        isWord = false;
    }


}

public class Autocomplete {

    private Node root;

    Autocomplete(){
        root = new Node();
    }

    private void insert(String word) {

        Node insertionNode = root;

        for(Character c : word.toCharArray()){
            insertionNode.children.putIfAbsent(c,new Node());
            insertionNode = insertionNode.children.get(c);
        }
        insertionNode.isWord = true;
    }

    private List<String> autocomplete(String prefix) {
        List<String> result = new ArrayList<>();

        Node autoCompleteNode = root;


        for(Character c : prefix.toCharArray()){
            if(!autoCompleteNode.children.containsKey(c)){
                return result;
            }
            autoCompleteNode = autoCompleteNode.children.get(c);
        }

        searchWord(prefix,autoCompleteNode,result);

        return result;
    }

    private void searchWord(String prefix, Node autoCompleteNode, List<String> result) {
        if (autoCompleteNode.isWord) {
            result.add(prefix);
        }
        for (char ch : autoCompleteNode.children.keySet()) {
            searchWord(prefix + ch, autoCompleteNode.children.get(ch), result);
        }
    }


    public static void main(String[] args) {



        Autocomplete engine = new Autocomplete();
        engine.insert("cat");
        engine.insert("camel");
        engine.insert("card");
        engine.insert("apple");

        System.out.println("Autocomplete result : " + engine.autocomplete("ca"));

    }



}
