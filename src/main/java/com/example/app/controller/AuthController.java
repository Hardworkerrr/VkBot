package com.example.app.controller;

import com.example.app.entity.CallbackRequest;
import com.example.app.enums.VkMethodType;
import com.example.app.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appAuth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public String auth(@RequestBody CallbackRequest callbackRequest){
        return authService.getConfirmationCode(VkMethodType.GET_AUTH_CODE.value(), callbackRequest.getGroupId());
    }
}
