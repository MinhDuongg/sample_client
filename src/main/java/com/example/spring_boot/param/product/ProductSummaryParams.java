package com.example.spring_boot.param.product;

import com.example.spring_boot.net.ApiRequestParams;

public class ProductSummaryParams  extends ApiRequestParams {

    String productKey;
    String version;


    private ProductSummaryParams(String version, String productKey) {
        this.version = version;
        this.productKey = productKey;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String productKey;
        private String version;

        public ProductSummaryParams build() {
            return new ProductSummaryParams(version, productKey);
        }

        public Builder productKey(String productKey) {
            this.productKey = productKey;
            return this;
        }

        public Builder version(String version) {
            this.version = version;
            return this;
        }


    }
}
