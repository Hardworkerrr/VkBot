package com.example.app.controller;


import com.example.app.entity.CallbackRequest;
import com.example.app.entity.VKMessage;
import com.example.app.entity.VKObject;
import com.example.app.enums.VkMethodType;
import com.example.app.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @PostMapping
    public String sendMessageToUser(@RequestBody CallbackRequest callbackRequest){
        VKObject vkObject = callbackRequest.getVkObject();
        if(vkObject instanceof VKMessage vkMessage){
            messageService.sendMessage(VkMethodType.SEND_MESSAGE.value(), vkMessage);
        }
        return "ok";
    }


}
