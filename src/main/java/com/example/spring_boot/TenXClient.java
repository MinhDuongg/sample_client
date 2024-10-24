package com.example.spring_boot;

import com.example.spring_boot.net.Authorization;
import com.example.spring_boot.net.LiveTenXResponseGetter;
import com.example.spring_boot.net.TenXResponseGetter;

import java.net.Proxy;

public class TenXClient {
    private final TenXResponseGetter responseGetter;

    /**
     * Constructs a StripeClient with default settings, using the provided API key. Use the builder
     * instead if you require more complex configuration.
     */
    public TenXClient(String apiKey) {
        this.responseGetter =
                new LiveTenXResponseGetter(builder().setApiKey(apiKey).buildOptions(), null);
    }

    public static TenxCli builder() {
        return new StripeClientBuilder();
    }

    public static final class TenXClientBuilder {
        private Authorization authenticator;
        private String actorID;
        private String actorType;
        private int connectTimeout = TenX.DEFAULT_CONNECT_TIMEOUT;
        private int readTimeout = TenX.DEFAULT_READ_TIMEOUT;
        private int maxNetworkRetries;
        private Proxy connectionProxy;
    }
}
