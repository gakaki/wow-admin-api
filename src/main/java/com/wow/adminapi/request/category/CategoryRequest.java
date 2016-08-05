package com.wow.adminapi.request.category;

import com.wow.common.request.ApiRequest;

/**
 * Created by win7 on 2016/8/3.
 */
public class CategoryRequest extends ApiRequest{

    private static final long serialVersionUID = 1L;

    private Integer categoryId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
