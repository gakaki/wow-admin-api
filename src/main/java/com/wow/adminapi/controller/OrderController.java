package com.wow.adminapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wow.adminapi.request.order.OrderDeliverRequest;
import com.wow.common.request.ApiRequest;
import com.wow.common.response.ApiResponse;
import com.wow.common.response.CommonResponse;
import com.wow.common.util.BeanUtil;
import com.wow.common.util.ErrorCodeUtil;
import com.wow.common.util.JsonUtil;
import com.wow.order.service.OrderService;
import com.wow.order.vo.OrderDeliverQuery;

/**
 * Created by zhengzhiqing on 16/7/2.
 */
@RestController
@RequestMapping(value = "/v1/order")
public class OrderController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 订单发货
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/deliver", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ApiResponse deliverGoods(ApiRequest request) {
        OrderDeliverRequest orderDeliverRequest = JsonUtil.fromJSON(request.getParamJson(), OrderDeliverRequest.class);
        ApiResponse apiResponse = new ApiResponse();
        //判断json格式参数是否有误
        if (orderDeliverRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        CommonResponse commonResponse = null;
        try {
            OrderDeliverQuery query = new OrderDeliverQuery();
            BeanUtil.copyProperties(orderDeliverRequest, query);

            commonResponse = orderService.deliverGoods(query);
            //如果处理失败 则返回错误信息
            if (ErrorCodeUtil.isFailedResponse(commonResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, commonResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("订单发货错误---" + e);
            setInternalErrorResponse(apiResponse);
        }

        return apiResponse;
    }

}