package com.example.network.api;

import org.apache.http.client.methods.HttpGet;

import java.util.HashMap;
import java.util.Map;

public abstract class ApiRequest {
    public String method = HttpGet.METHOD_NAME;

    public abstract String getUrlString();

    public Map<String, String> getParameters() {
        return new HashMap<String, String>();
    }

    public Map<String, String> getHeaders() {
        return new HashMap<String, String>();
    }

    public String getMethod() {
        return method;
    }

    public String getPostBody() {
        return null;
    }
}
