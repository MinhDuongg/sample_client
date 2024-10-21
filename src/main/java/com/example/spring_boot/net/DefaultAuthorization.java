package com.example.spring_boot.net;

public class DefaultAuthorization implements Authorization {

    private final String apiKey;
     public DefaultAuthorization(String apiKey) {
         this.apiKey = apiKey;
     }

    @Override
    public String getApiKey() {
        return this.apiKey;
    }
}
