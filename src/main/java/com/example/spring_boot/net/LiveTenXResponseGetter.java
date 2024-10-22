package com.example.spring_boot.net;

import com.example.spring_boot.exception.TenXException;
import com.example.spring_boot.model.TenXObjectInterface;
import com.google.gson.JsonSyntaxException;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Optional;

public class LiveTenXResponseGetter implements TenXResponseGetter {
    private final HttpClient httpClient;
    private final TenXResponseGetterOptions options;

    public LiveTenXResponseGetter(HttpClient httpClient) {
        this(null, httpClient);
    }


    @FunctionalInterface
    private interface RequestSendFunction<R> {
        R apply(TenXRequest request) throws TenXException;
    }

    @Override
    public <T extends TenXObjectInterface> T request(BaseAddress baseAddress, ApiResource.RequestMethod method, String path, Map<String, Object> params, Type typeToken, RequestOptions options) throws Exception {
        return null;
    }

    public LiveTenXResponseGetter(TenXResponseGetterOptions options, HttpClient httpClient) {
        this.options = options != null ? options : GlobalTenXResponseGetterOptions.INSTANCE;
        this.httpClient = (httpClient != null) ? httpClient : buildDefaultHttpClient();
    }

    private TenXRequest toTenXRequest(ApiRequest apiRequest, RequestOptions mergedOptions)
            throws TenXException {
        String fullUrl = fullUrl(apiRequest);

        TenXRequest request =
                TenXRequest.create(
                        apiRequest.getMethod(),
                        fullUrl,
                        apiRequest.getParams(),
                        mergedOptions);

        return request;
    }

    @Override
    public <T extends TenXObjectInterface> T request(ApiRequest apiRequest, Type typeToken)
            throws TenXException {

        RequestOptions mergedOptions = RequestOptions.merge(this.options, apiRequest.getOptions());

        TenXRequest request = toTenXRequest(apiRequest, mergedOptions);
        TenXResponse response =
                sendWithTelemetry(request, apiRequest.getUsage(), r -> httpClient.requestWithRetries(r));

        int responseCode = response.code();
        String responseBody = response.body();
        String requestId = response.requestId();

        if (responseCode < 200 || responseCode >= 300) {
            handleError(response, apiRequest.getApiMode());
        }

        T resource = null;
        try {
            resource = (T) ApiResource.deserializeStripeObject(responseBody, typeToken, this);
        } catch (JsonSyntaxException e) {
            throw makeMalformedJsonError(responseBody, responseCode, requestId, e);
        }

        if (resource instanceof StripeCollectionInterface<?>) {
            ((StripeCollectionInterface<?>) resource).setRequestOptions(apiRequest.getOptions());
            ((StripeCollectionInterface<?>) resource).setRequestParams(apiRequest.getParams());
        }

        if (resource instanceof com.stripe.model.v2.StripeCollection<?>) {
            ((com.stripe.model.v2.StripeCollection<?>) resource)
                    .setRequestOptions(apiRequest.getOptions());
        }

        resource.setLastResponse(response);

        return resource;
    }

    private static HttpClient buildDefaultHttpClient() {
        return new HttpURLConnectionClient();
    }


    private String fullUrl(BaseApiRequest apiRequest) {
        BaseAddress baseAddress = apiRequest.getBaseAddress();
        RequestOptions options = apiRequest.getOptions();
        String relativeUrl = apiRequest.getPath();
        String baseUrl;
        switch (baseAddress) {
            case API:
                baseUrl = this.options.getApiBase();
                break;
            default:
                throw new IllegalArgumentException("Unknown base address " + baseAddress);
        }
        if (options != null && options.getBaseUrl() != null) {
            baseUrl = options.getBaseUrl();
        }
        return String.format("%s%s", baseUrl, relativeUrl);
    }
}
