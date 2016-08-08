package com.wow.adminapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wow.common.page.PageModel;
import com.wow.common.request.ApiRequest;
import com.wow.common.response.ApiResponse;
import com.wow.common.response.CommonResponse;
import com.wow.common.util.ErrorCodeUtil;
import com.wow.common.util.JsonUtil;
import com.wow.common.util.StringUtil;
import com.wow.common.util.ValidatorUtil;
import com.wow.product.service.ProductService;
import com.wow.product.vo.request.ProductCreateRequest;
import com.wow.product.vo.request.ProductPageRequest;
import com.wow.product.vo.response.ProductPageResponse;

@RestController
@CrossOrigin(maxAge = 3600)
public class ProductController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    /**
     * 创建产品
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/product/create", method = RequestMethod.POST)
    public ApiResponse createProductInfo(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();
        ProductCreateRequest productCreateRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), ProductCreateRequest.class);

        //判断json格式参数是否有误
        if (productCreateRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        String errorMsg = ValidatorUtil.getError(productCreateRequest);
        //如果校验错误 则返回
        if (StringUtil.isNotEmpty(errorMsg)) {
            setInvalidParameterResponse(apiResponse, errorMsg);
            return apiResponse;
        }

        try {
            CommonResponse commonResponse = productService.createProductInfo(productCreateRequest);

            if (ErrorCodeUtil.isFailedResponse(commonResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, commonResponse);
            }
        } catch (Exception e) {
            logger.error("创建产品发生错误---" + e);
            e.printStackTrace();
            setInternalErrorResponse(apiResponse);
        }
        return apiResponse;
    }


    /**
     * 查询产品分页列表
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/product/pageList", method = RequestMethod.GET)
    public ApiResponse getProductPageList(ApiRequest apiRequest) {
        logger.info("start to get product on page");
        ApiResponse apiResponse = new ApiResponse();

        ProductPageRequest productPageRequest = JsonUtil
                .fromJSON(apiRequest.getParamJson(), ProductPageRequest.class);
        //判断json格式参数是否有误
        if (productPageRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }
        
        PageModel pageModel = new PageModel();
        if (productPageRequest.getPageSize() != null) {
        	pageModel.setShowCount(productPageRequest.getPageSize());
        }
        if (productPageRequest.getCurrentPage() != null) {
        	pageModel.setCurrentPage(productPageRequest.getCurrentPage());
        	//仅在第一页时获取相应的分页记录
            if (productPageRequest.getCurrentPage() == 1) {
            	pageModel.setIsPage(true);
            }
        }
        pageModel.setModel(productPageRequest);
        
        try {
        	ProductPageResponse productPageResponse = productService.getProductListPage(pageModel);       	
        	
            //如果处理失败 则返回错误信息
            if (ErrorCodeUtil.isFailedResponse(productPageResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, productPageResponse);
            } else {
                apiResponse.setData(productPageResponse);
            }
        } catch (Exception e) {
            logger.error("查找product错误---" + e);
            setInternalErrorResponse(apiResponse);
        }
        return apiResponse;
    }
}