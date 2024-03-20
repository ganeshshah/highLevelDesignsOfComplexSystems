package com.example.shardingimplementation;

import com.example.shardingimplementation.algorithm.DirectoryBasedSharding;
import com.example.shardingimplementation.repository.DirectoryBasedRepo;


import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DirectoryBasedShardingImplementation {

    public static void main(String[] args) throws InterruptedException {

        DirectoryBasedRepo shard1 = new DirectoryBasedRepo();
        DirectoryBasedRepo shard2 = new DirectoryBasedRepo();
        DirectoryBasedRepo shard3 = new DirectoryBasedRepo();
        DirectoryBasedRepo shard4 = new DirectoryBasedRepo();

        ArrayList<DirectoryBasedRepo> listOfShards = new ArrayList<>();
        listOfShards.add(shard1);
        listOfShards.add(shard2);
        listOfShards.add(shard3);
        listOfShards.add(shard4);
        DirectoryBasedSharding ds = new DirectoryBasedSharding();
        ds.setShardList(listOfShards);


        ExecutorService fixedExecutorService = Executors.newFixedThreadPool(5);


        for(int i=0; i<35; i++){
            fixedExecutorService.submit(() -> {
                try {
                    ds.insertData("dummyDatabaseValue-");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

        }
        Thread.sleep(1000);

        for(int i=0; i<10; i++){
            int finalI = i;
            fixedExecutorService.submit(() -> {
                System.out.println("Value in DB for Key : " + finalI + " " + ds.readEntriesFromDB(finalI));
            });

        }

        Thread.sleep(1000);
        fixedExecutorService.shutdown();

        System.out.println("Total rows in each shard : ");
        for(DirectoryBasedRepo shards : listOfShards){
            System.out.println(shards.getDatabaseSize());
        }

    }
}
