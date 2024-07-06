package com.example.app.service.impl;

import com.example.app.entity.VKMessage;
import com.example.app.entity.VKObject;
import com.example.app.service.HttpRequestSender;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class MessageService implements HttpRequestSender {
    @Value("${vk.api.url}")
    private String vkApiUrl;
    @Value("${vk.api.version}")
    private String vkApiVersion;
    @Value("${vk.api.access_token}")
    private String token;
    private final HttpClient httpClient;

    @Autowired
    public MessageService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
    public void sendMessage(String methodType, VKObject vkObject) {
        if (vkObject instanceof VKMessage vkMessage) {
            Map<String, Object> params = new HashMap<>();
            String requestUrl = vkApiUrl + methodType;
            params.put("access_token", token);
            params.put("v", vkApiVersion);
            params.put("user_id", vkMessage.getUserId());
            params.put("message", vkMessage.getBody());
            params.put("random_id", new Random().nextInt(1000000000));
            requestUrl = generateRequestUrl(requestUrl, params);
            sendGetRequestToApi(requestUrl);
        } else {
            throw new RuntimeException("Wrong VkObject for this type of service");
        }
    }

    @SneakyThrows
    @Override
    public HttpResponse<String> sendGetRequestToApi(String requestUrl) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .GET()
                .build();
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

}
