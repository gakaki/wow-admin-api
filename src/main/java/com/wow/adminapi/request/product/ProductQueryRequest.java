package com.wow.adminapi.request.product;

import com.wow.common.request.ApiRequest;

public class ProductQueryRequest extends ApiRequest {
    private static final long serialVersionUID = 1L;

    private int productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}