package com.design.ratelimitter.controller;



import com.design.ratelimitter.dao.RateLimiterDAO;
import com.design.ratelimitter.request.ReadRequest;
import com.design.ratelimitter.request.UpdateRequest;
import com.design.ratelimitter.response.ReadResponse;
import com.design.ratelimitter.response.UpdateResponse;

import java.util.concurrent.CompletableFuture;

public class RateLimiter {
    private RateLimiterDAO dao;

    public CompletableFuture<UpdateResponse> updateKeyMetrics(UpdateRequest request) {
        return dao.updateLimit(request.getServiceName(), request.getKey(), request.getCount(), request.getTimestamp())
                .thenApply(__ -> new UpdateResponse());
    }

    public CompletableFuture<ReadResponse> readKeyMetrics(ReadRequest request) {
        return dao.readLimit(request.getServiceName());
    }
}
