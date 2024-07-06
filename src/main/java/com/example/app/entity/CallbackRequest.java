package com.example.app.entity;

import com.example.app.enums.EventType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class CallbackRequest {
    private EventType type;
    @JsonProperty("event_id")
    private String eventId;
    @JsonProperty("v")
    private String version;
    @JsonProperty("object")
    private VKObject vkObject;
    @JsonProperty("group_id")
    private String groupId;
}
