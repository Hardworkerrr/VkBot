package com.example.app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class VKMessage extends VKObject{
    private long id;
    private int out;
    @JsonProperty("read_state")
    private int readState;
    private String title;
    private String body;
    @JsonProperty("owner_ids")
    private List<Integer> ownerIds;
}
