package com.example.spring_boot.net;

import lombok.experimental.Accessors;

@Accessors(fluent = true)
public class AbstractTenXResponse<T> {
    /** The HTTP status code of the response. */
    int code;

    /** The HTTP headers of the response. */
    HttpHeaders headers;

    /** The body of the response. */
    T body;

    public final int code() {
        return this.code;
    }

    public final HttpHeaders headers() {
        return this.headers;
    }

    public final T body() {
        return this.body;
    }

}
