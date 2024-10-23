package com.example.spring_boot;

import com.example.spring_boot.net.LiveTenXResponseGetter;
import com.example.spring_boot.net.TenXResponseGetter;

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

    public static StripeClientBuilder builder() {
        return new StripeClientBuilder();
    }
}
