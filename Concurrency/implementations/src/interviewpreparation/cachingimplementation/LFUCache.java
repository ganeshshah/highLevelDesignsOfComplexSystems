package interviewpreparation.cachingimplementation;

import java.util.*;

class LFUCache<K, V> {
    private final int capacity;
    private Map<K, V> cache;
    private Map<K, Integer> frequency;
    private Map<Integer, LinkedHashSet<K>> frequencyMap;
    private int minFrequency;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        frequency = new HashMap<>();
        frequencyMap = new HashMap<>();
        minFrequency = 0;
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        increaseFrequency(key);
        return cache.get(key);
    }

    public void put(K key, V value) {
        if (capacity == 0) {
            return;
        }
        if (cache.containsKey(key)) {
            cache.put(key, value);
            increaseFrequency(key);
            return;
        }
        if (cache.size() >= capacity) {
            evict();
        }
        cache.put(key, value);
        frequency.put(key, 1);
        frequencyMap.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        minFrequency = 1;
    }

    private void increaseFrequency(K key) {
        int freq = frequency.get(key);
        frequency.put(key, freq + 1);
        frequencyMap.get(freq).remove(key);
        if (frequencyMap.get(freq).isEmpty()) {
            frequencyMap.remove(freq);
            if (freq == minFrequency) {
                minFrequency++;
            }
        }
        frequencyMap.computeIfAbsent(freq + 1, k -> new LinkedHashSet<>()).add(key);
    }

    private void evict() {
        LinkedHashSet<K> minFreqSet = frequencyMap.get(minFrequency);
        K keyToRemove = minFreqSet.iterator().next();
        minFreqSet.remove(keyToRemove);
        if (minFreqSet.isEmpty()) {
            frequencyMap.remove(minFrequency);
        }
        cache.remove(keyToRemove);
        frequency.remove(keyToRemove);
    }
}
