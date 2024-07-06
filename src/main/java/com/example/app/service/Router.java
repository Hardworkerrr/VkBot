package com.example.app.service;

import com.example.app.entity.CallbackRequest;

public interface Router {
    String routeRequest(CallbackRequest callbackRequest);
}
