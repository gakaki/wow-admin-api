package com.wow.adminapi.controller;

import com.wow.common.request.ApiRequest;
import com.wow.common.response.ApiResponse;
import com.wow.product.service.DesignerService;
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
public class DesignerController {

    @Autowired
    private DesignerService designerService;

    @RequestMapping(value="/v1/designer/queryAllDesigner",method = RequestMethod.GET)
    public ApiResponse queryAllDesigner(ApiRequest apiRequest){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setData(designerService.getAllDesigners());
        return apiResponse;
    }
}
