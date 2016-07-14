package com.wow.adminapi.controller;

import com.wow.common.request.ApiRequest;
import com.wow.common.response.ApiResponse;
import com.wow.common.util.JsonUtil;
import com.wow.common.util.StringUtil;
import com.wow.common.util.ValidatorUtil;
import com.wow.order.service.OrderService;
import com.wow.order.vo.request.OrderRequest;
import com.wow.order.vo.response.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengzhiqing on 16/7/2.
 */
@RestController
@RequestMapping
public class OrderController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 获取订单列表信息
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/v1/orders", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResponse getOrderList(ApiRequest request) {
        OrderRequest orderRequest = JsonUtil.fromJSON(request.getParamJson(), OrderRequest.class);
        ApiResponse apiResponse = new ApiResponse();
        //判断json格式参数是否有误
        if (orderRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        String errorMsg = ValidatorUtil.getError(orderRequest);
        //如果校验错误 则返回
        if (StringUtil.isNotEmpty(errorMsg)) {
            setInvalidParameterResponse(apiResponse, errorMsg);
            return apiResponse;
        }
        OrderResponse orderResponse = null;
        try {
            orderResponse = orderService.queryOrderById(orderRequest.getId());
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(orderResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, orderResponse);
            } else {
                apiResponse.setData(orderResponse.getOrder());
            }
        } catch (Exception e) {
            logger.error("获取订单列表信息错误---" + e);
            setInternalErrorResponse(apiResponse);
        }
        return apiResponse;
    }

}