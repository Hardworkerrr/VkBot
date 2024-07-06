package com.example.app.handler.impl;

import com.example.app.controller.MessageController;
import com.example.app.entity.CallbackRequest;
import com.example.app.enums.EventType;
import com.example.app.handler.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewMessageRequestHandler implements RequestHandler {

    private final MessageController messageController;

    @Autowired
    public NewMessageRequestHandler(MessageController messageController) {
        this.messageController = messageController;
    }

    @Override
    public String processCallbackRequest(CallbackRequest callbackRequest) {
        return messageController.sendMessageToUser(callbackRequest);
    }

    @Override
    public String callbackType() {
        return EventType.MESSAGE_NEW.value();
    }
}
