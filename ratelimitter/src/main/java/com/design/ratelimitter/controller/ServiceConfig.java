package com.design.ratelimitter.controller;



import com.design.ratelimitter.dao.ServiceConfigDAO;
import com.design.ratelimitter.models.ServiceConfiguration;
import com.design.ratelimitter.request.ReadConfigRequest;
import com.design.ratelimitter.request.ServiceConfigurationRequest;
import com.design.ratelimitter.response.ServiceConfigurationResponse;
import com.design.ratelimitter.response.UpdateConfigResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ServiceConfig {
    private ServiceConfigDAO dao;

    public CompletableFuture<UpdateConfigResponse> updateServiceConfig(ServiceConfigurationRequest request) {
        return dao.updateServiceConfig(new ServiceConfiguration(request.getService(),
                        request.getKey(),
                        TimeUnit.valueOf(request.getTimeUnit()),
                        request.getLimit()))
                .thenApply(__ -> new UpdateConfigResponse());
    }

    public CompletableFuture<ServiceConfigurationResponse> readServiceConfig(ReadConfigRequest readConfigRequest) {
        return dao.readConfig(readConfigRequest.getServiceName())
                .thenApply(config -> new ServiceConfigurationResponse(config));
    }
}
