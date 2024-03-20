package com.example.shardingimplementation.algorithm;

import com.example.shardingimplementation.repository.KeyValueRepository;

import java.util.List;

public class KeyBasedSharding {
    private static volatile Integer id = 0;
    private Integer totalShards = 0;
    private List<KeyValueRepository> listOfShards;

    public KeyBasedSharding(List<KeyValueRepository> listOfShards) {
        this.listOfShards = listOfShards;
        this.totalShards = listOfShards.size();
    }

    public synchronized void insertData(String name){
        id++;
        Integer shardId = id % totalShards;
        listOfShards.get(shardId).insertData(id,name);
    }

}
