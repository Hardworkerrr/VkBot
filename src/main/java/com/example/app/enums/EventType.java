package com.example.app.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EventType {
    @JsonProperty("message_new")
    MESSAGE_NEW("message_new"),
    @JsonProperty("confirmation")
    AUTH("confirmation");
    private final String type;

    EventType(String type) {
        this.type = type;
    }

    public String value() {
        return this.type;
    }

}
