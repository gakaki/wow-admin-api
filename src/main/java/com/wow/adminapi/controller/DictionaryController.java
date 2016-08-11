package com.wow.adminapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wow.adminapi.request.dictionary.DictionaryListRequest;
import com.wow.adminapi.response.dictionary.DictionaryResponse;
import com.wow.adminapi.vo.DictionaryVo;
import com.wow.common.model.Dictionary;
import com.wow.common.request.ApiRequest;
import com.wow.common.request.DictionaryRequest;
import com.wow.common.response.ApiResponse;
import com.wow.common.response.CommonResponse;
import com.wow.common.service.DictionaryService;
import com.wow.common.util.BeanUtil;
import com.wow.common.util.DictionaryUtil;
import com.wow.common.util.JsonUtil;
import com.wow.common.util.StringUtil;
import com.wow.common.util.ValidatorUtil;

/**
 * Created by win7 on 2016/7/27.
 */
@RestController
@CrossOrigin(maxAge = 3600)
public class DictionaryController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    @Autowired
    private DictionaryService dictionaryService;

    /**
     *
     * @param apiRequest
     * @return 返回字典信息
     */
    @RequestMapping(value="/v1/dictionarys",method = RequestMethod.GET)
    public ApiResponse getCountryList(ApiRequest apiRequest){
        ApiResponse apiResponse = new ApiResponse();
        DictionaryListRequest dictionaryListRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), DictionaryListRequest.class);
        
        //判断json格式参数是否有误
        if (dictionaryListRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        String errorMsg = ValidatorUtil.getError(dictionaryListRequest);
        //如果校验错误 则返回
        if (StringUtil.isNotEmpty(errorMsg)) {
            setInvalidParameterResponse(apiResponse, errorMsg);
            return apiResponse;
        }
        
        List<String> dictionaryGroupList = dictionaryListRequest.getKeyGroups();
        Map<String,List<DictionaryVo>> dictionaryMap = new HashMap<String,List<DictionaryVo>>();
        try {
        	
        	for(String dictionryGroup : dictionaryGroupList){
        		List<Dictionary> dictionaryList=DictionaryUtil.getKeyGroup(dictionryGroup);
        		List<DictionaryVo> dictionaryVoList = new ArrayList<DictionaryVo>();
        		for(Dictionary dictionary:dictionaryList){
        			DictionaryVo dv = new DictionaryVo();
        			dv.setKeyValue(dictionary.getKeyValue());
        			dv.setKeyId(dictionary.getKeyId());
        			
        			dictionaryVoList.add(dv);
        		}
        		dictionaryMap.put(dictionryGroup, dictionaryVoList);
        	}
        	DictionaryResponse dictionaryVoResponse = new DictionaryResponse();
        	dictionaryVoResponse.setDictionaryList(dictionaryMap);
            apiResponse.setData(dictionaryVoResponse.getDictionaryList());
        } catch (Exception e) {
            logger.error("查找国家信息错误---" , e);
            setInternalErrorResponse(apiResponse);
        }


        return apiResponse;
    }
}
