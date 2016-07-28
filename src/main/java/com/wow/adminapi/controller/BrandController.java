package com.wow.adminapi.controller;

import com.wow.common.response.ApiResponse;
import com.wow.product.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by win7 on 2016/7/28.
 */
@RestController
@CrossOrigin(maxAge = 3600)
public class BrandController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private BrandService brandService;

    @RequestMapping(value="/v1/brand/queryAll",method = RequestMethod.GET)
    public ApiResponse queryAllBrand(){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setData(brandService.getAllBrands());
        return apiResponse;
    }
}
