package com.wow.adminapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengzhiqing on 16/6/22.
 */
@RestController
@RequestMapping("/")
public class ApiSummaryController {
    private static final Logger logger = LoggerFactory.getLogger(ApiSummaryController.class);

    @RequestMapping(method = RequestMethod.GET)
    public Map<String,String> findAllApis() {
        Map<String,String> apiMap = new HashMap<String,String>();
        apiMap.put("attributes_url","http://localhost:8080/admin-api-dev/v1.0/attributes/{attribute_id}");
        apiMap.put("users_url","http://localhost:8080/admin-api-dev/v1.0/users/{user_id}");
        apiMap.put("products_url","http://localhost:8080/admin-api-dev/v1.0/products/{product_id}");
        apiMap.put("orders_url","http://localhost:8080/admin-api-dev/v1.0/orders/{order_id}");
        apiMap.put("prices_url","http://localhost:8080/admin-api-dev/v1.0/prices/{product_id}");
        apiMap.put("stocks_url","http://localhost:8080/admin-api-dev/v1.0/stocks/{product_id}");
        apiMap.put("coupons_url","http://localhost:8080/admin-api-dev/v1.0/coupons/{coupon_id}");
        return apiMap;
    }
}
