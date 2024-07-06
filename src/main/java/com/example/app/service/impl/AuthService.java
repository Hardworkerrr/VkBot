package com.example.app.service.impl;

import com.example.app.service.HttpRequestSender;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService implements HttpRequestSender {

    @Value("${vk.api.url}")
    private String vkApiUrl;
    @Value("${vk.api.version}")
    private String vkApiVersion;
    @Value("${vk.api.access_token}")
    private String token;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public AuthService(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }


    public String getConfirmationCode(String methodType, String groupId) {
        Map<String, Object> params = new HashMap<>();
        String requestUrl = vkApiUrl + methodType;
        params.put("access_token", token);
        params.put("v", vkApiVersion);
        params.put("group_id", groupId);
        requestUrl = generateRequestUrl(requestUrl, params);
        HttpResponse<String> httpResponse = sendGetRequestToApi(requestUrl);
        return getCode(httpResponse.body());
    }

    @SneakyThrows
    private String getCode(String body) {
        JsonNode rootNode = objectMapper.readTree(body);
        JsonNode codeNode = rootNode.path("response").path("code");
        return codeNode.asText();
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
