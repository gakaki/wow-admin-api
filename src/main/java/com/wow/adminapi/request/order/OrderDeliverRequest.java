package com.wow.adminapi.request.order;

import java.io.Serializable;
import java.util.List;

/**
 * 创建发货操作请求类
 * 
 * @author chenkaiwei
 * @version $Id: V1.0 2016年7月12日 下午4:50:51 Exp $
 */
public class OrderDeliverRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    //订单项目id列表
    private List<Integer> saleOrderItemIds;

    //用户id
    private Integer endUserId;

    //订单号
    private String orderCode;

    //订单id
    private Integer orderId;

    //配送方式1. 尖叫合作物流，2. 供应商合作物流 3.京东 4.日日顺
    private Byte deliveryMothod;

    //物流公司代码
    private String deliveryCompanyCode;

    //运单号
    private String deliveryOrderNo;

    public Byte getDeliveryMothod() {
        return deliveryMothod;
    }

    public void setDeliveryMothod(Byte deliveryMothod) {
        this.deliveryMothod = deliveryMothod;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public List<Integer> getSaleOrderItemIds() {
        return saleOrderItemIds;
    }

    public void setSaleOrderItemIds(List<Integer> saleOrderItemIds) {
        this.saleOrderItemIds = saleOrderItemIds;
    }

    public Integer getEndUserId() {
        return endUserId;
    }

    public void setEndUserId(Integer endUserId) {
        this.endUserId = endUserId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getDeliveryCompanyCode() {
        return deliveryCompanyCode;
    }

    public void setDeliveryCompanyCode(String deliveryCompanyCode) {
        this.deliveryCompanyCode = deliveryCompanyCode;
    }

    public String getDeliveryOrderNo() {
        return deliveryOrderNo;
    }

    public void setDeliveryOrderNo(String deliveryOrderNo) {
        this.deliveryOrderNo = deliveryOrderNo;
    }

}
