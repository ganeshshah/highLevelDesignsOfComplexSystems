package com.example.shardingimplementation;

import com.example.shardingimplementation.algorithm.KeyBasedSharding;
import com.example.shardingimplementation.repository.KeyValueRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ShardingImplementationApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ShardingImplementationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		KeyValueRepository shard1 = new KeyValueRepository();
		KeyValueRepository shard2 = new KeyValueRepository();
		KeyValueRepository shard3 = new KeyValueRepository();
		KeyValueRepository shard4 = new KeyValueRepository();

		List<KeyValueRepository> listOfShards = List.of(shard1,shard2,shard3,shard4);

		KeyBasedSharding keyBasedSharding = new KeyBasedSharding(listOfShards);

		ExecutorService fixedExecutorService = Executors.newFixedThreadPool(5);


		for(int i=0; i<100000;i++){
			fixedExecutorService.submit(() -> {
				keyBasedSharding.insertData("dummyDatabaseValue");
			});

		}

		fixedExecutorService.shutdown();

		Thread.sleep(1000);

		for(KeyValueRepository shards : listOfShards){
			System.out.println(shards.getDatabaseSize());
		}

	}
}
