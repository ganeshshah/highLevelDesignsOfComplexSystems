package com.example.shardingimplementation.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class DirectoryBasedRepo {

    public static final Integer maximumCapacity = 10;

    public volatile AtomicInteger dbSize = new AtomicInteger(0);
    private final Map<Integer,String> database = new ConcurrentHashMap<>();

    public void insertData(Integer id,String name){
        synchronized (database){
            database.put(id,name);
            dbSize.incrementAndGet();
        }
    }

    public Integer getDatabaseSize() throws InterruptedException {
            return database.size();
    }

    public Map<Integer,String> getDatabase(){
        return this.database;
    }

}
