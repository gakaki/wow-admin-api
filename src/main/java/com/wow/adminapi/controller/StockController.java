package com.wow.adminapi.controller;

import com.wow.common.response.ApiResponse;
import com.wow.stock.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhengzhiqing on 16/6/23.
 */
@RestController
@RequestMapping("/v1/stocks")
public class StockController {
    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ApiResponse getProductWarehouses(@PathVariable Integer productId) {
        logger.info("start to get warehouses in which product stored");
        ApiResponse apiResponse = new ApiResponse();
        List<Integer> warehouseList = stockService.selectWarehouseByProductId(productId);
//        responseUtil.setResponse(apiResponse,"0");
        apiResponse.setData(warehouseList);
        return apiResponse;
    }
}
