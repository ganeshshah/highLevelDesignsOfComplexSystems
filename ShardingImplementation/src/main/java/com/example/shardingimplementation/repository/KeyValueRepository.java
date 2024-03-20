package com.example.shardingimplementation.repository;


import java.util.HashMap;
import java.util.Map;



public class KeyValueRepository {

    private Map<Integer,String> database = new HashMap<>();

    public void insertData(Integer id,String name){
        database.put(id,name);
    }

    public Integer getDatabaseSize(){
        if(database == null)
            return 0;
        return database.size();
    }
}
