package com.example.spring_boot;

import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;

public abstract class TenX {
    public static final int DEFAULT_CONNECT_TIMEOUT = 30 * 1000;
    public static final int DEFAULT_READ_TIMEOUT = 80 * 1000;

    public static final String TEST_CONNECTIVITY = "https://api.sandbox.10xbanking.com/traefik/ping";
    public static final String LIVE_API_BASE = "https://api.sandbox.10xbanking.com";

    public static volatile String apiKey;
    public static volatile String actorId;

    private static volatile int connectTimeout = -1;
    private static volatile int readTimeout = -1;


    private static volatile int maxNetworkRetries = 2;

    private static volatile String apiBase = LIVE_API_BASE;
    private static volatile String testConnectivity = TEST_CONNECTIVITY;

    private static volatile Proxy connectionProxy = null;
    private static volatile PasswordAuthentication proxyCredential = null;
    private static volatile Map<String, String> appInfo = null;

    /**
     * (FOR TESTING ONLY) If you'd like your API requests to hit your own (mocked) server, you can set
     * this up here by overriding the base api URL.
     */
    public static void overrideApiBase(final String overriddenApiBase) {
        apiBase = overriddenApiBase;
    }

    public static String getApiBase() {
        return apiBase;
    }


    /**
     * Set proxy to tunnel all connections.
     *
     * @param proxy proxy host and port setting
     */
    public static void setConnectionProxy(final Proxy proxy) {
        connectionProxy = proxy;
    }

    public static Proxy getConnectionProxy() {
        return connectionProxy;
    }

    /**
     * Returns the connection timeout.
     *
     * @return timeout value in milliseconds
     */
    public static int getConnectTimeout() {
        if (connectTimeout == -1) {
            return DEFAULT_CONNECT_TIMEOUT;
        }

        return connectTimeout;
    }

    /**
     * Sets the timeout value that will be used for making new connections to the Stripe API (in
     * milliseconds).
     *
     * @param timeout timeout value in milliseconds
     */
    public static void setConnectTimeout(final int timeout) {
        connectTimeout = timeout;
    }

    /**
     * Returns the read timeout.
     *
     * @return timeout value in milliseconds
     */
    public static int getReadTimeout() {
        if (readTimeout == -1) {
            return DEFAULT_READ_TIMEOUT;
        }
        return readTimeout;
    }

    /**
     * Sets the timeout value that will be used when reading data from an established connection to
     * the API (in milliseconds).
     *
     * <p>Note that this value should be set conservatively because some API requests can take time
     * and a short timeout increases the likelihood of causing a problem in the backend.
     *
     * @param timeout timeout value in milliseconds
     */
    public static void setReadTimeout(final int timeout) {
        readTimeout = timeout;
    }

    /**
     * Returns the maximum number of times requests will be retried.
     *
     * @return the maximum number of times requests will be retried
     */
    public static int getMaxNetworkRetries() {
        return maxNetworkRetries;
    }

    /**
     * Sets the maximum number of times requests will be retried.
     *
     * @param numRetries the maximum number of times requests will be retried
     */
    public static void setMaxNetworkRetries(final int numRetries) {
        maxNetworkRetries = numRetries;
    }

    /**
     * Provide credential for proxy authorization if required.
     *
     * @param auth proxy required userName and password
     */
    public static void setProxyCredential(final PasswordAuthentication auth) {
        proxyCredential = auth;
    }

    public static PasswordAuthentication getProxyCredential() {
        return proxyCredential;
    }

    public static void setAppInfo(String name) {
        setAppInfo(name, null, null, null);
    }

    public static void setAppInfo(String name, String version) {
        setAppInfo(name, version, null, null);
    }

    public static void setAppInfo(String name, String version, String url) {
        setAppInfo(name, version, url, null);
    }

    /**
     * Sets information about your application. The information can be used as attributes field in the request.
     *
     */
    public static void setAppInfo(String name, String version, String url, String partnerId) {
        if (appInfo == null) {
            appInfo = new HashMap<String, String>();
        }

        appInfo.put("name", name);
        appInfo.put("version", version);
        appInfo.put("url", url);
        appInfo.put("partner_id", partnerId);
    }

    public static Map<String, String> getAppInfo() {
        return appInfo;
    }

}