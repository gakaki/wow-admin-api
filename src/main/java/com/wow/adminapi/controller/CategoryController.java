package com.wow.adminapi.controller;

import com.wow.attribute.service.CategoryService;
import com.wow.attribute.vo.request.CategoryQueryRequest;
import com.wow.attribute.vo.response.CategoryListResponse;
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
 * Created by zhengzhiqing on 16/7/10.
 */
@RestController
@CrossOrigin(maxAge = 3600)
public class CategoryController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/v1/category/first-level", method = RequestMethod.GET)
    public ApiResponse getFirstLevelCategories(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();
//        CategoryQueryRequest categoryQueryRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), CategoryQueryRequest.class);
//        //判断json格式参数是否有误
//        if (categoryQueryRequest == null) {
//            setParamJsonParseErrorResponse(apiResponse);
//            return apiResponse;
//        }
        try {
            CategoryListResponse categoryListResponse = categoryService.getFirstLevelCategory();
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(categoryListResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, categoryListResponse);
            } else {
                apiResponse.setData(categoryListResponse.getCategoryList());
            }
        } catch (Exception e) {
            logger.error("查找一级类目发生错误---" + e);
            setInternalErrorResponse(apiResponse);
        }
        return apiResponse;

    }

    @RequestMapping(value = "/v1/category/sub-category", method = RequestMethod.GET)
    public ApiResponse getSubCategories(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();
        CategoryQueryRequest categoryQueryRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), CategoryQueryRequest.class);
        //判断json格式参数是否有误
        if (categoryQueryRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }
        int categoryId = categoryQueryRequest.getCategoryId();
        try {
            CategoryListResponse categoryListResponse = categoryService.getSubCategory(categoryId);
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(categoryListResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, categoryListResponse);
            } else {
                apiResponse.setData(categoryListResponse.getCategoryList());
            }
        } catch (Exception e) {
            logger.error("查找子类目发生错误---" + e);
            setInternalErrorResponse(apiResponse);
        }
        return apiResponse;
    }

    @RequestMapping(value = "/v1/category/path-category", method = RequestMethod.GET)
    public ApiResponse getCategoryPath(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();

        CategoryQueryRequest categoryQueryRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), CategoryQueryRequest.class);
        if (categoryQueryRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        try {
            CategoryListResponse categoryListResponse = categoryService.getCategoryPath(categoryQueryRequest.getCategoryId());

            if (!isServiceCallSuccess(categoryListResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, categoryListResponse);
            } else {
                apiResponse.setData(categoryListResponse);
            }
        } catch (Exception e) {
            logger.error("查询类目路径错误---" + e);
            e.printStackTrace();
            setInternalErrorResponse(apiResponse);
        }

        return apiResponse;
    }
}
