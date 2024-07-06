package com.example.app.util;

import com.example.app.entity.VKMessage;
import com.example.app.entity.VKObject;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VKObjectDeserializer extends StdDeserializer<VKObject> {

    public VKObjectDeserializer() {
        this(null);
    }

    public VKObjectDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public VKObject deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        if (node.has("body") && node.has("title")) {
            VKMessage vkMessage = new VKMessage();
            vkMessage.setId(node.get("id").asInt());
            vkMessage.setOut(node.get("out").asInt());
            vkMessage.setTitle(node.get("title").asText());
            vkMessage.setOwnerIds(parseOwnerIds(node.get("owner_ids")));
            vkMessage.setReadState(node.get("read_state").asInt());
            vkMessage.setBody(node.get("body").asText());
            vkMessage.setUserId(node.get("user_id").asInt());
            vkMessage.setDate(node.get("date").asLong());
            return vkMessage;
        } else {
            throw new IllegalArgumentException("Unknown type of VKObject");
        }
    }

    private List<Integer> parseOwnerIds(JsonNode jsonNode) {
        List<Integer> ownerIds = new ArrayList<>();
        if (jsonNode != null && jsonNode.isArray()) {
            for (JsonNode idNode : jsonNode) {
                ownerIds.add(idNode.asInt());
            }
        }
        return ownerIds;
    }
}
