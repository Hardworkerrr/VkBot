package com.example.app.service.impl;

import com.example.app.entity.CallbackRequest;
import com.example.app.handler.RequestHandler;
import com.example.app.service.Router;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoutingService implements Router {
    private final Map<String, RequestHandler> handlers = new HashMap<>();

    public void registerHandler(RequestHandler requestHandler) {
        if (handlers.containsKey(requestHandler.callbackType())) {
            throw new IllegalArgumentException("Duplicate key in map - " + requestHandler.callbackType());
        } else
            handlers.put(requestHandler.callbackType(), requestHandler);
    }

    @Override
    public String routeRequest(CallbackRequest callbackRequest) {
        RequestHandler requestHandler = handlers.get(callbackRequest.getType().value());
        if(requestHandler!=null){
            return requestHandler.processCallbackRequest(callbackRequest);
        }
        throw new RuntimeException("Event of this type can't be handled");
    }
}
