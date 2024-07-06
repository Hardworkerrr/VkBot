package com.example.app.service;

import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public interface HttpRequestSender {
    HttpResponse<?> sendGetRequestToApi(String requestUrl);
    default String generateRequestUrl(String requestUrl, Map<String, Object> params){
        StringBuffer queryParams = new StringBuffer(requestUrl + "?");
        params.forEach((key, value) -> {
            queryParams.append(URLEncoder.encode(key, StandardCharsets.UTF_8));
            queryParams.append("=");
            queryParams.append(URLEncoder.encode(value.toString(), StandardCharsets.UTF_8));
            queryParams.append("&");
        });
        return queryParams.toString();
    }
}
