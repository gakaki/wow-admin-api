package com.wow.adminapi.controller;

import com.wow.common.response.ApiResponse;
import com.wow.product.model.Brand;
import com.wow.product.model.Designer;
import com.wow.product.service.BrandService;
import com.wow.product.vo.response.ProductBrandResponse;
import com.wow.product.vo.response.ProductDesignerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
