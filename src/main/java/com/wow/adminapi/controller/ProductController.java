package com.wow.adminapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wow.adminapi.request.product.ProductQueryRequest;
import com.wow.common.page.PageModel;
import com.wow.common.request.ApiRequest;
import com.wow.common.response.ApiResponse;
import com.wow.common.response.CommonResponse;
import com.wow.common.util.ErrorCodeUtil;
import com.wow.common.util.JsonUtil;
import com.wow.common.util.StringUtil;
import com.wow.common.util.ValidatorUtil;
import com.wow.product.model.Product;
import com.wow.product.service.ProductService;
import com.wow.product.vo.request.ProductCreateRequest;
import com.wow.product.vo.request.ProductPageRequest;
import com.wow.product.vo.request.ProductUpdateRequest;
import com.wow.product.vo.request.ProductUpdateImagesRequest;
import com.wow.product.vo.request.ProductUpdateSerialsRequest;
import com.wow.product.vo.response.ProductDetailResponse;
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
            logger.error("创建产品发生错误---" , e);
            e.printStackTrace();
            setInternalErrorResponse(apiResponse);
        }
        return apiResponse;
    }

    @RequestMapping(value = "/v1/product/delete", method = RequestMethod.POST)
    public ApiResponse deleteProduct(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();

        ProductQueryRequest productQueryRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), ProductQueryRequest.class);

        if (productQueryRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        String errorMsg = ValidatorUtil.getError(productQueryRequest);
        if (StringUtil.isNotEmpty(errorMsg)) {
            setInvalidParameterResponse(apiResponse, errorMsg);
            return apiResponse;
        }

        return deleteProductRest(productQueryRequest.getProductId());
    }

    @RequestMapping(value = "/v1/products/{productId}", method = RequestMethod.DELETE)
    private ApiResponse deleteProductRest(@PathVariable Integer productId) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            CommonResponse commonResponse = productService.deleteProduct(productId);

            if (ErrorCodeUtil.isFailedResponse(commonResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, commonResponse);
            }
        } catch (Exception e) {
            logger.error("删除产品错误---", e);
            setInternalErrorResponse(apiResponse);
        }
        return apiResponse;
    }

    /**
     * 查询产品的详细信息,包括基本信息、系列产品和图文信息。
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/product/detail", method = RequestMethod.GET)
    public ApiResponse getProductDetail(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();

        ProductQueryRequest productQueryRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), ProductQueryRequest.class);
        if (productQueryRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        String errorMsg = ValidatorUtil.getError(productQueryRequest);
        if (StringUtil.isNotEmpty(errorMsg)) {
            setInvalidParameterResponse(apiResponse, errorMsg);
            return apiResponse;
        }

        try {
            ProductDetailResponse productDetailResponse = productService.getProductDetail(productQueryRequest.getProductId());
            if (ErrorCodeUtil.isFailedResponse(productDetailResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, productDetailResponse);
            } else {
                apiResponse.setData(productDetailResponse);
            }
        } catch (Exception e) {
            logger.error("查询产品详细信息错误---", e);
            setInternalErrorResponse(apiResponse);
        }

        return apiResponse;
    }

    @RequestMapping(value = "/v1/product/info", method = RequestMethod.POST)
    public ApiResponse updateProductInfo(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();

        ProductUpdateRequest productUpdateRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), ProductUpdateRequest.class);
        if (productUpdateRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        String errorMsg = ValidatorUtil.getError(productUpdateRequest);
        if (StringUtil.isNotEmpty(errorMsg)) {
            setInvalidParameterResponse(apiResponse, errorMsg);
            return apiResponse;
        }

        try {
            CommonResponse commonResponse = productService.updateProductInfo(productUpdateRequest);
            if (ErrorCodeUtil.isFailedResponse(commonResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, commonResponse);
            }
        } catch (Exception e) {
            logger.error("更新产品描述信息错误---", e);
            setInternalErrorResponse(apiResponse);
        }

        return apiResponse;
    }

    @RequestMapping(value = "/v1/product/serials", method = RequestMethod.POST)
    public ApiResponse updateProductSerials(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();

        ProductUpdateSerialsRequest productUpdateSerialsRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), ProductUpdateSerialsRequest.class);
        if (productUpdateSerialsRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        String errorMsg = ValidatorUtil.getError(productUpdateSerialsRequest);
        if (StringUtil.isNotEmpty(errorMsg)) {
            setInvalidParameterResponse(apiResponse, errorMsg);
            return apiResponse;
        }

        try {
            CommonResponse commonResponse = productService.updateProductSerials(productUpdateSerialsRequest);
            if (ErrorCodeUtil.isFailedResponse(commonResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, commonResponse);
            }
        } catch (Exception e) {
            logger.error("更新子产品信息错误---", e);
            setInternalErrorResponse(apiResponse);
        }

        return apiResponse;
    }

    @RequestMapping(value = "/v1/product/images", method = RequestMethod.POST)
    public ApiResponse updateProductImages(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();

        ProductUpdateImagesRequest jsonRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), ProductUpdateImagesRequest.class);
        if (jsonRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        String errorMsg = ValidatorUtil.getError(jsonRequest);
        if (StringUtil.isNotEmpty(errorMsg)) {
            setInvalidParameterResponse(apiResponse, errorMsg);
            return apiResponse;
        }

        try {
            CommonResponse commonResponse = productService.updateProductImages(jsonRequest);
            if (ErrorCodeUtil.isFailedResponse(commonResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, commonResponse);
            }
        } catch (Exception e) {
            logger.error("更新产品的图片信息错误---", e);
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
            logger.error("查找product错误---" , e);
            setInternalErrorResponse(apiResponse);
        }
        return apiResponse;
    }
}
