package com.wow.adminapi.controller;


import com.wow.common.request.ApiRequest;
import com.wow.common.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengzhiqing on 16/7/12.
 */
@RestController
public class ShippingInfoController extends BaseController{

    /**
     * 创建/添加收货信息
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/user/shippinginfo", method = RequestMethod.POST)
    ApiResponse createShippingInfo(ApiRequest apiRequest) {
        return null;
    }

    /**
     * 更新收货信息
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/user/shippinginfo", method = RequestMethod.PUT)
    ApiResponse updateShippingInfo(ApiRequest apiRequest) {
        return null;
    }

    /**
     * 删除收货信息
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/user/shippinginfo", method = RequestMethod.DELETE)
    ApiResponse deleteShippingInfo(ApiRequest apiRequest) {
        return null;
    }

    /**
     * 查询用户所有收货信息
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/user/shippinginfo", method = RequestMethod.GET)
    ApiResponse getShippingInfoByUserId(ApiRequest apiRequest) {
        return null;
    }

}
