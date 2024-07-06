package com.example.app.handler;

import com.example.app.entity.CallbackRequest;
import com.example.app.service.impl.RoutingService;
import org.springframework.beans.factory.annotation.Autowired;

public interface RequestHandler {
    String processCallbackRequest(CallbackRequest callbackRequest);
    String callbackType();

    @Autowired
    default void registerHandler(RoutingService routingService){
        routingService.registerHandler(this);
    }

}
