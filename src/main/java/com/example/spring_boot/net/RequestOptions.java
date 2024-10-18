package com.example.spring_boot.net;

import java.net.PasswordAuthentication;
import java.net.Proxy;

public class RequestOptions {
    private final String idempotencyKey;
    private final String actorType;
    private final String actorId;
    private final String baseUrl;

    private final Integer connectTimeout;
    private final Integer readTimeout;
    private final Integer maxNetworkRetries;
    private final Proxy connectionProxy;

    public static RequestOptions getDefault() {
        return new RequestOptions(
                null, null, null, null, null, null, null, null, null, null, null, null);
    }

    protected RequestOptions(
            String idempotencyKey,
            String clientId,
            String stripeVersionOverride,
            String baseUrl,
            Integer connectTimeout,
            Integer readTimeout,
            Integer maxNetworkRetries,
            Proxy connectionProxy,
            PasswordAuthentication proxyCredential) {
        this.authenticator = authenticator;
        this.clientId = clientId;
        this.idempotencyKey = idempotencyKey;
        this.stripeContext = stripeContext;
        this.stripeAccount = stripeAccount;
        this.stripeVersionOverride = stripeVersionOverride;
        this.baseUrl = baseUrl;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.maxNetworkRetries = maxNetworkRetries;
        this.connectionProxy = connectionProxy;
        this.proxyCredential = proxyCredential;
    }
}
