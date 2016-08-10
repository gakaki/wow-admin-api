package com.wow.adminapi.controller;

import com.wow.adminapi.request.dictionary.DictionaryListRequest;
import com.wow.adminapi.response.dictionary.DictionaryResponse;
import com.wow.adminapi.vo.DictionaryVo;
import com.wow.common.model.Country;
import com.wow.common.model.Dictionary;
import com.wow.common.request.ApiRequest;
import com.wow.common.response.ApiResponse;
import com.wow.common.service.CountryService;
import com.wow.common.util.DictionaryUtil;
import com.wow.common.util.JsonUtil;
import com.wow.common.util.StringUtil;
import com.wow.common.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by win7 on 2016/8/10.
 */
@RestController
public class CountryController extends BaseController{

    @Autowired
    private CountryService countryService;

    @RequestMapping(value="/v1/queryAllCountries",method = RequestMethod.GET)
    public ApiResponse getCountryList(ApiRequest apiRequest){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(countryService.queryAllCountry());
        return apiResponse;
    }
}
