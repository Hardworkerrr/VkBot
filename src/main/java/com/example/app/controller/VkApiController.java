package com.example.app.controller;

import com.example.app.entity.CallbackRequest;
import com.example.app.service.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vkApi")
public class VkApiController {

    private final Router routingService;

    @Autowired
    public VkApiController(Router routingService) {
        this.routingService = routingService;
    }

    @PostMapping
    public String processRequestRouting(@RequestBody CallbackRequest callbackRequest){
        return routingService.routeRequest(callbackRequest);
    }
}
