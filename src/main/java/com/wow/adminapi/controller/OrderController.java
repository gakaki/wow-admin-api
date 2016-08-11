package com.wow.adminapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wow.adminapi.request.order.AdminOrderListRequest;
import com.wow.adminapi.request.order.OrderDeliverRequest;
import com.wow.adminapi.request.order.OrderDetailRequest;
import com.wow.common.request.ApiRequest;
import com.wow.common.response.ApiResponse;
import com.wow.common.response.CommonResponse;
import com.wow.common.util.BeanUtil;
import com.wow.common.util.DateUtil;
import com.wow.common.util.ErrorCodeUtil;
import com.wow.common.util.JsonUtil;
import com.wow.common.util.StringUtil;
import com.wow.order.service.AdminOrderService;
import com.wow.order.service.OrderService;
import com.wow.order.vo.AdminOrderListQuery;
import com.wow.order.vo.OrderDeliverQuery;
import com.wow.order.vo.OrderDetailQuery;
import com.wow.order.vo.response.AdminOrderDetailResponse;
import com.wow.order.vo.response.AdminOrderListResponse;

/**
 * Created by zhengzhiqing on 16/7/2.
 */
@RestController
@RequestMapping(value = "/v1/order")
@CrossOrigin(maxAge = 3600)
public class OrderController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private AdminOrderService adminOrderService;

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
            logger.error("订单发货错误---" , e);
            setInternalErrorResponse(apiResponse);
        }

        return apiResponse;
    }

    /**
     * 获取用户订单列表 后台接口使用
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/getList", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ApiResponse selectOrderList(ApiRequest request) {
        AdminOrderListRequest orderListRequest = JsonUtil.fromJSON(request.getParamJson(), AdminOrderListRequest.class);
        ApiResponse apiResponse = new ApiResponse();
        //判断json格式参数是否有误
        if (orderListRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        AdminOrderListResponse orderListResponse = null;
        try {
            AdminOrderListQuery query = new AdminOrderListQuery();
            BeanUtil.copyProperties(orderListRequest, query);

            //设置订单开始日期
            if (StringUtil.isNotEmpty(orderListRequest.getBeginDate())) {
                query.setBeginDateFormat(DateUtil.setBeginDate(orderListRequest.getBeginDate()));
            }

            //设置订单结束日期
            if (StringUtil.isNotEmpty(orderListRequest.getEndDate())) {
                query.setEndDateFormat(DateUtil.setEndDate(orderListRequest.getEndDate()));
            }

            orderListResponse = adminOrderService.queryOrderListPage(query);
            //如果处理失败 则返回错误信息
            if (ErrorCodeUtil.isFailedResponse(orderListResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, orderListResponse);
            } else {
                apiResponse.setData(orderListResponse);
            }
        } catch (Exception e) {
            logger.error("获取订单列表错误---" , e);
            setInternalErrorResponse(apiResponse);
        }

        return apiResponse;
    }

    /**
     * 获取订单明细 后台用
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderDetail", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ApiResponse getOrderDetail(ApiRequest request) {
        OrderDetailRequest orderDetailRequest = JsonUtil.fromJSON(request.getParamJson(), OrderDetailRequest.class);
        ApiResponse apiResponse = new ApiResponse();
        //判断json格式参数是否有误
        if (orderDetailRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        AdminOrderDetailResponse orderDetailResponse = null;
        try {
            OrderDetailQuery query = new OrderDetailQuery();
            query.setOrderCode(orderDetailRequest.getOrderCode());

            orderDetailResponse = adminOrderService.queryOrderDetail(query);
            //如果处理失败 则返回错误信息
            if (ErrorCodeUtil.isFailedResponse(orderDetailResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, orderDetailResponse);
            } else {
                apiResponse.setData(orderDetailResponse);
            }
        } catch (Exception e) {
            logger.error("获取订单明细错误---" , e);
            setInternalErrorResponse(apiResponse);
        }

        return apiResponse;
    }

}