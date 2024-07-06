package com.example.app.handler.impl;

import com.example.app.controller.AuthController;
import com.example.app.entity.CallbackRequest;
import com.example.app.enums.EventType;
import com.example.app.handler.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthRequestHandler implements RequestHandler {

    private final AuthController authController;

    @Autowired
    public AuthRequestHandler(AuthController authController) {
        this.authController = authController;
    }

    @Override
    public String processCallbackRequest(CallbackRequest callbackRequest) {
        return authController.auth(callbackRequest);
    }

    @Override
    public String callbackType() {
        return EventType.AUTH.value();
    }

}
