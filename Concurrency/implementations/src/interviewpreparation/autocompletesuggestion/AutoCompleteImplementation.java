package interviewpreparation.autocompletesuggestion;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SearchNode {
    Map<Character, SearchNode> children;
    boolean isWord;

    SearchNode() {
        children = new HashMap<>();
        isWord = false;
    }
}

public class AutoCompleteImplementation {

    private SearchNode rootNode;

    AutoCompleteImplementation() {
        rootNode = new SearchNode();
    }

    private void insert(String word) {

        SearchNode searchNode = rootNode;

        for (Character c : word.toCharArray()) {
            searchNode.children.putIfAbsent(c, new SearchNode());
            searchNode = searchNode.children.get(c);
        }

        searchNode.isWord = true;
    }


    private List<String> autocomplete(String word) {

        List<String> result = new ArrayList<>();

        SearchNode autoCompleteNode = rootNode;

        for (Character c : word.toCharArray()) {
            if (!autoCompleteNode.children.containsKey(c))
                return result; // prefix not found

            autoCompleteNode = autoCompleteNode.children.get(c);
        }

        collectSuggestedWords(word, autoCompleteNode, result);
        return result;

    }

    private void collectSuggestedWords(String word, SearchNode autoCompleteNode, List<String> result) {

        if(autoCompleteNode.isWord)
            result.add(word);

        for(Character c : autoCompleteNode.children.keySet()){
            collectSuggestedWords(word + c, autoCompleteNode.children.get(c), result);
        }

    }


    public static void main(String[] args) {

        AutoCompleteImplementation engine = new AutoCompleteImplementation();

        engine.insert("cat");
        engine.insert("car");
        engine.insert("card");
        engine.insert("cards");

        System.out.println("Autocomplete for word 'cat' is : " + engine.autocomplete("car"));

    }


}
