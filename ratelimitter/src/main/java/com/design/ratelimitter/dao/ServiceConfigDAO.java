package com.design.ratelimitter.dao;



import com.design.ratelimitter.models.ServiceConfiguration;

import java.util.concurrent.CompletableFuture;

public class ServiceConfigDAO {
    public CompletableFuture<Void> updateServiceConfig(ServiceConfiguration configuration){
        //insert into service_config values (configuration)
        return null;
    }

    public CompletableFuture<ServiceConfiguration> readConfig(String serviceName) {
        //select * from service_config where name = serviceName;
        return null;
    }
}
