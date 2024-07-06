package com.example.app.enums;

public enum VkMethodType {
    SEND_MESSAGE("messages.send"),
    GET_AUTH_CODE("groups.getCallbackConfirmationCode");
    private final String type;

    VkMethodType(String type) {
        this.type = type;
    }

    public String value() {
        return this.type;
    }
}
