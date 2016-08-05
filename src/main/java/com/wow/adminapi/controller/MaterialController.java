package com.wow.adminapi.controller;

import com.wow.adminapi.request.category.CategoryRequest;
import com.wow.attribute.service.CategoryMaterialService;
import com.wow.attribute.vo.response.MaterialResponse;
import com.wow.common.request.ApiRequest;
import com.wow.common.response.ApiResponse;
import com.wow.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by win7 on 2016/8/4.
 */
@RestController
@CrossOrigin(maxAge = 3600)
public class MaterialController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private CategoryMaterialService categoryMaterialService;

    /**
     *
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/material/queryCategoryMaterial", method = RequestMethod.GET)
    public MaterialResponse queryMaterialsByCategory(ApiRequest apiRequest){
        ApiResponse apiResponse = new ApiResponse();
        CategoryRequest categoryRequest = JsonUtil
                .fromJSON(apiRequest.getParamJson(), CategoryRequest.class);
        int categoryId=0;
        if (categoryRequest != null) {
            categoryId=categoryRequest.getCategoryId();
        }
        return categoryMaterialService.queryMaterialsByCategory(categoryId);
    }
}
