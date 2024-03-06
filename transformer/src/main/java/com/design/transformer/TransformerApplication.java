package com.design.transformer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

import static com.design.transformer.JsonToMapConverter.jsonToHashMap;

@SpringBootApplication
public class TransformerApplication implements CommandLineRunner {

	public static void main(String[] args){
		SpringApplication.run(TransformerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		HashMap<String,Object> result =  jsonToHashMap(jsonString);
		System.out.printf("hello");
	}

	static String jsonString = "{\n" +
			"  \"totalCount\": 2,\n" +
			"  \"results\": [\n" +
			"    {\n" +
			"      \"alertsEnabled\": true,\n" +
			"      \"aliases\": [\n" +
			"        {\n" +
			"          \"hostDetails\": [\n" +
			"            {\n" +
			"              \"hostId\": \"12.34.56.78\"\n" +
			"            },\n" +
			"            {\n" +
			"              \"hostId\": \"12.34.56.78\"\n" +
			"            }\n" +
			"          ]\n" +
			"        },\n" +
			"        {\n" +
			"          \"hostDetails\": [\n" +
			"            {\n" +
			"              \"hostId\": \"12.34.56.78\"\n" +
			"            },\n" +
			"            {\n" +
			"              \"hostId\": \"12.34.56.78\"\n" +
			"            }\n" +
			"          ]\n" +
			"        }\n" +
			"      ],\n" +
			"      \"authMechanismName\": \"SCRAM-SHA-1\",\n" +
			"      \"clusterId\": \"{CLUSTER-ID}\",\n" +
			"      \"created\": \"2014-04-22T19:56:50Z\",\n" +
			"      \"groupId\": \"{PROJECT-ID}\",\n" +
			"      \"hasStartupWarnings\": false,\n" +
			"      \"hidden\": false,\n" +
			"      \"hostEnabled\": true,\n" +
			"      \"hostname\": \"{HOSTNAME}\",\n" +
			"      \"id\": \"{HOST-ID}\",\n" +
			"      \"ipAddress\": \"127.0.0.1\",\n" +
			"      \"journalingEnabled\": false,\n" +
			"      \"lastDataSizeBytes\": 633208918,\n" +
			"      \"lastIndexSizeBytes\": 101420524,\n" +
			"      \"lastPing\": \"2016-08-18T11:23:41Z\",\n" +
			"      \"links\": [],\n" +
			"      \"logsEnabled\": false,\n" +
			"      \"lowUlimit\": false,\n" +
			"      \"muninEnabled\": false,\n" +
			"      \"port\": 26000,\n" +
			"      \"profilerEnabled\": false,\n" +
			"      \"replicaSetName\": \"rs1\",\n" +
			"      \"replicaStateName\": \"PRIMARY\",\n" +
			"      \"sslEnabled\": true,\n" +
			"      \"systemInfo\": {\n" +
			"        \"memSizeMB\": 65536,\n" +
			"        \"numCores\": 16\n" +
			"      },\n" +
			"      \"typeName\": \"REPLICA_PRIMARY\",\n" +
			"      \"uptimeMsec\": 1827300394,\n" +
			"      \"username\": \"mongo\",\n" +
			"      \"version\": \"4.0.0\"\n" +
			"    },\n" +
			"    {}\n" +
			"  ]\n" +
			"}";
}
