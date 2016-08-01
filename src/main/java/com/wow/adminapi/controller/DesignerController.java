package com.wow.adminapi.controller;

import com.wow.common.request.ApiRequest;
import com.wow.common.response.ApiResponse;
import com.wow.product.model.Designer;
import com.wow.product.service.DesignerService;
import com.wow.product.vo.response.ProductDesignerResponse;
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
public class DesignerController {

    @Autowired
    private DesignerService designerService;

    @RequestMapping(value="/v1/designer/queryAllDesigner",method = RequestMethod.GET)
    public ApiResponse queryAllDesigner(ApiRequest apiRequest){
        ApiResponse apiResponse=new ApiResponse();
        ProductDesignerResponse designers=designerService.getAllDesigners();
        List<Designer> designerList=null;
        List<Map<String,Object>> list=new ArrayList();
        Map<String,Object> designer=null;
        if(designers!=null){
            designerList=designers.getDesignerList();
            for(int i=0;i<designerList.size();i++){
                designer=new HashMap<String,Object>();
                designer.put("designerId",designerList.get(i).getId());
                designer.put("designerName",designerList.get(i).getDesignerName());
                list.add(designer);
            }
            designer=null;
            designerList=null;
            designers=null;
        }
        apiResponse.setData(list);
        return apiResponse;
    }
}
