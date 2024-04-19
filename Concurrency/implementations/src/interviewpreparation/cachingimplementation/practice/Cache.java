package interviewpreparation.cachingimplementation.practice;


import java.util.HashMap;
import java.util.Map;

class Node {

    int key;
    int val;
    Node prev;
    Node next;

    Node(int key, int val) {
        this.key = key;
        this.val = val;
    }

}

public class Cache {
    private int capacity;
    private Map<Integer, Node> cacheData;
    private Node head;
    private Node tail;

    Cache(int capacity) {
        this.capacity = capacity;
        this.cacheData = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    void put(int key, int val) {

        if (cacheData.containsKey(key)) {
            Node removeOldData = cacheData.get(key);
            removeNode(removeOldData);
        }

        Node data = new Node(key, val);
        cacheData.put(key, data);
        addNode(data);

        if (cacheData.size() > capacity) {
            Node firstNode = head.next;
            removeNode(firstNode);
            cacheData.remove(firstNode.key);
        }

    }

    int get(int key) {
        if (!cacheData.containsKey(key))
            return -1;

        Node data = cacheData.get(key);
        removeNode(data); // remove the node from doubly linked list
        addNode(data); // add the recently accessed node to the tail of the list
        return data.val;
    }

    private void addNode(Node data) {
        Node lastNode = tail.prev;
        lastNode.next = data;
        data.prev = lastNode;
        data.next = tail;
        tail.prev = data;
    }

    private void removeNode(Node data) {
        Node prevNode = data.prev;
        prevNode.next = data.next;
        data.next.prev = prevNode;
    }

    public static void main(String[] args) {

        Cache lruCache = new Cache(4);
        lruCache.put(1,5);
        lruCache.put(3,5);
        lruCache.put(2,5);
        lruCache.put(4,5);
        lruCache.get(3);
        lruCache.put(5,5);
        lruCache.put(6,5);


        for(Map.Entry<Integer,Node> entry : lruCache.cacheData.entrySet()){
            System.out.println("key : " + entry.getValue().key + " value : " + entry.getValue().val);
        }

    }

}
