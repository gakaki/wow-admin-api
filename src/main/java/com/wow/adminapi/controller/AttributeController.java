package com.wow.adminapi.controller;


import com.wow.attribute.model.Attribute;
import com.wow.attribute.service.AttributeService;
import com.wow.common.response.ApiResponse;
import com.wow.common.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class AttributeController {

    private static final Logger logger = LoggerFactory.getLogger(AttributeController.class);

    @Autowired
    private AttributeService attributeService;

    @RequestMapping(value = "/v1/attributes", method = RequestMethod.POST)
    public ApiResponse createAttribute(@Validated @RequestBody Attribute newAttribute, BindingResult result) {
        ApiResponse apiResponse = new ApiResponse();
        if (result.hasErrors()) {
            apiResponse.setResCode("40000");
            apiResponse.setResMsg(ValidatorUtil.getError(result));
        } else {
            logger.info("start to add attribute:" + newAttribute);
            int createdCnt = attributeService.createAttribute(newAttribute);
            apiResponse.setResCode("0");
            apiResponse.setResMsg("success");
            apiResponse.setData(createdCnt);
        }
        return apiResponse;
    }

    @RequestMapping(value = "/v1/attributes/{id}", method = RequestMethod.GET)
    public ApiResponse findAttribute(@PathVariable Integer id) {
        ApiResponse apiResponse = new ApiResponse();
        logger.info("get attribute, id=" + id);
        if(id==null || id <=0 ) {
//            responseUtil.setResponse(apiResponse, "40401");
        } else {
            try {
                Attribute attribute = attributeService.getAttributeById(id);
                if (attribute != null) {
                    apiResponse.setResCode("0");
                    apiResponse.setResMsg("");
                    apiResponse.setData(attribute);
                } else {
                    apiResponse.setResCode("0");
                    apiResponse.setResMsg("找不到属性:" + id);
                    apiResponse.setData(attribute);
                }
            }catch (Exception e) {
                logger.error(e.getMessage());
                apiResponse.setResCode("50100");
                apiResponse.setResMsg("Internal error:" + e.getMessage());
            }

        }
        return apiResponse;
    }

    @RequestMapping(value = "/v1/attributes", method = RequestMethod.GET)
    public ApiResponse findAttributes() {
        logger.info("get all attributes");
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResCode("0");
        apiResponse.setResMsg("");
        apiResponse.setData(attributeService.getAllAttributes());
        return apiResponse;
    }

    @RequestMapping(value = "/v2.0/attributes/{id}", method = RequestMethod.GET)
    public ApiResponse findAttributeV2(@PathVariable Integer id) {
        ApiResponse apiResponse = new ApiResponse();
        logger.info("get attribute, id=" + id);
        if(id==null || id <=0 ) {
//            responseUtil.setResponse(apiResponse, "40401");
        } else {
            try {
                Attribute attribute = attributeService.getAttributeById(id);
                if (attribute != null) {
                    apiResponse.setResCode("0");
                    apiResponse.setResMsg("");
                    apiResponse.setData(attribute);
                } else {
                    apiResponse.setResCode("0");
                    apiResponse.setResMsg("找不到属性:" + id);
                    apiResponse.setData(attribute);
                }
            }catch (Exception e) {
                logger.error(e.getMessage());
                apiResponse.setResCode("50100");
                apiResponse.setResMsg("Internal error:" + e.getMessage());
            }

        }
        return apiResponse;
    }

    @RequestMapping(value = "/v1/attributes/{id}", method = RequestMethod.PATCH)
    public ApiResponse updateAttributeById(@RequestBody Attribute updatedAttribute, @PathVariable Integer id) {
        ApiResponse apiResponse = new ApiResponse();
        logger.info("update attribute, id=" + id);
        if (updatedAttribute.getId() == null) {
            updatedAttribute.setId(id);
        }
        int count = attributeService.updateAttribute(updatedAttribute);
        apiResponse.setResCode("0");
        apiResponse.setResMsg("success");
        apiResponse.setData(count);
        return apiResponse;
    }

    @RequestMapping(value = "/v1/attributes/{id}", method = RequestMethod.DELETE)
    public ApiResponse deleteAttribute(@PathVariable Integer id) {
        ApiResponse apiResponse = new ApiResponse();
        logger.info("delete attribute, id=" + id);
        int count = attributeService.deleteAttributeById(id);
        apiResponse.setResCode("0");
        apiResponse.setResMsg("success");
        apiResponse.setData(count);
        return apiResponse;
    }
}