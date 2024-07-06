package com.example.app.entity;

import com.example.app.util.VKObjectDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@JsonDeserialize(using = VKObjectDeserializer.class)
public abstract class VKObject {
    @JsonProperty("user_id")
    private int userId;
    private long date;
}
