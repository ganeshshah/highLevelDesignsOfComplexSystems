package com.example.shardingimplementation.algorithm;

import com.example.shardingimplementation.repository.DirectoryBasedRepo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.shardingimplementation.repository.DirectoryBasedRepo.maximumCapacity;

public class DirectoryBasedSharding {

    // It provides flexibility to add and remove shard dynamically without the need of re-balancing shards

    private static volatile HashMap<Integer,Integer> lookupTable = new HashMap<>();

    private static volatile AtomicInteger minimumValue = new AtomicInteger(Integer.MAX_VALUE);
    private static volatile AtomicInteger shardId = new AtomicInteger(0);

    private static volatile AtomicInteger id = new AtomicInteger(0);
    private static volatile ArrayList<DirectoryBasedRepo> listOfShards;

    public void setShardList(ArrayList<DirectoryBasedRepo> listOfShards) {
        this.listOfShards = listOfShards;
    }

    public void insertData(String name) throws InterruptedException {
            synchronized (listOfShards){
                getShardWithMinimumEntries();
                listOfShards.get(shardId.get()).insertData(id.incrementAndGet(),name + id.get());
                lookupTable.put(id.get(),shardId.get());
            }
    }

    private void getShardWithMinimumEntries() throws InterruptedException {
        int minIndex = -1;
        int minValue = Integer.MAX_VALUE;
        boolean addNewShard = false;
        int stableShards = 0;

        // Find the shard with the minimum number of entries
        for (int i = 0; i < listOfShards.size(); i++) {
            int size = listOfShards.get(i).dbSize.get();
            if((size * 100.0 / maximumCapacity)  > 80 ){
                addNewShard = true;
            }else{
                stableShards++;
            }
            if (size < minValue) {
                minValue = size;
                minIndex = i;
            }
        }

        if(addNewShard && stableShards < 2){
            System.out.println("Notify Client : Shards capacity used more that 80 percent, Adding new shards for handling load");
            listOfShards.add(new DirectoryBasedRepo());
        }

        // Update shardId and minimumValue atomically
        if (minIndex != -1) {
            shardId.set(minIndex);
            minimumValue.set(minValue);
        }
    }



    public String readEntriesFromDB(Integer id){
        if(lookupTable.containsKey(id))
            return listOfShards.get(lookupTable.get(id)).getDatabase().get(id);
        return "The data doesn't exist for provided id : " + id;
    }


}
