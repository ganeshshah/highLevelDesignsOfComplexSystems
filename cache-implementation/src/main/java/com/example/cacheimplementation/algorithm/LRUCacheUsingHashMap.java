package com.example.cacheimplementation.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


class LRUCache {
    int capacity;
    LinkedHashMap<Integer, Integer> dic;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dic = new LinkedHashMap<>(5, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return dic.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        dic.put(key, value);
    }

}

public class LRUCacheUsingHashMap {

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

public static void main(String[] args) {

    LRUCache lruCache = new LRUCache(2);
    System.out.println(Runtime.getRuntime().availableProcessors());

    while(true){
        System.out.println("Cache value.....");
        for (Map.Entry<Integer,Integer> entry : lruCache.dic.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert data, key :");
        Integer key = sc.nextInt();
        System.out.println("Insert data, value :");
        Integer value = sc.nextInt();
        lruCache.put(key,value);

    }


}

}


