package com.example.spring_boot.net;

import java.util.Map;

public class ApiRequestParams {
    private static final ApiRequestParamsConverter PARAMS_CONVERTER = new ApiRequestParamsConverter();

    public Map<String, Object> toMap() {
        return PARAMS_CONVERTER.convert(this);
    }
}
