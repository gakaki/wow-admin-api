package com.wow.adminapi.request.order;

import com.wow.common.request.ApiRequest;

/**
 * 创建订单明细请求类
 * 
 * @author chenkaiwei
 * @version $Id: V1.0 2016年7月12日 下午4:50:51 Exp $
 */
public class OrderDetailRequest extends ApiRequest {

    private static final long serialVersionUID = 1L;

    //订单id
    private Integer orderId;
    
    //订单号
    private String orderCode;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

}
