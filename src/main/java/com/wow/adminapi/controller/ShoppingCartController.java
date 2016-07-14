package com.wow.adminapi.controller;

import com.wow.common.request.ApiRequest;
import com.wow.common.response.ApiResponse;
import com.wow.common.response.CommonResponse;
import com.wow.common.util.JsonUtil;
import com.wow.common.util.StringUtil;
import com.wow.common.util.ValidatorUtil;
import com.wow.user.service.ShoppingCartService;
import com.wow.user.vo.request.ShoppingCartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 购物车相关controller
 * 
 * @author chenkaiwei
 * @version $Id: V1.0 2016年7月13日 下午2:26:51 Exp $
 */
@RestController
@RequestMapping("/v1/cart")
public class ShoppingCartController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加指定的产品到购物车
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ApiResponse addProductIntoCart(ApiRequest request) {
        ShoppingCartRequest shoppingCartRequest = JsonUtil.fromJSON(request.getParamJson(), ShoppingCartRequest.class);
        ApiResponse apiResponse = new ApiResponse();

        //判断json格式参数是否有误
        if (shoppingCartRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        String errorMsg = ValidatorUtil.getError(shoppingCartRequest);
        //如果校验错误 则返回
        if (StringUtil.isNotEmpty(errorMsg)) {
            setInvalidParameterResponse(apiResponse, errorMsg);
            return apiResponse;
        }
        try {
            CommonResponse commonResponse = shoppingCartService.addProductIntoCart(shoppingCartRequest);
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(commonResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, commonResponse);
            }
        } catch (Exception e) {
            logger.error("添加产品到购物车信息错误---" + e);
            setInternalErrorResponse(apiResponse);
        }

        return apiResponse;
    }
}
