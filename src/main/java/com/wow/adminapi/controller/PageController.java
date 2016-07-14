package com.wow.adminapi.controller;

import com.wow.common.request.ApiRequest;
import com.wow.common.response.ApiResponse;
import com.wow.common.util.JsonUtil;
import com.wow.page.service.PageConfigService;
import com.wow.page.vo.request.PageRequest;
import com.wow.page.vo.response.PageBannerResponse;
import com.wow.page.vo.response.PageSceneResponse;
import com.wow.page.vo.response.PageTopicResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengzhiqing on 16/6/23.
 */
@RestController
public class PageController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private PageConfigService pageConfigService;

    @RequestMapping(value = "/v1/page/banners", method = RequestMethod.GET)
    public ApiResponse getBannersOnPage(ApiRequest apiRequest) {
        logger.info("start to get banners on page");
        ApiResponse apiResponse = new ApiResponse();
        PageRequest pageRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), PageRequest.class);
        //判断json格式参数是否有误
        if (pageRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        try {
            PageBannerResponse pageBannerResponse = pageConfigService.getBannersByPageType(pageRequest.getPageType());
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(pageBannerResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, pageBannerResponse);
            } else {
                apiResponse.setData(pageBannerResponse.getPageBannerConfigList());
            }
        } catch (Exception e) {
            logger.error("查找Banner错误---" + e);
            setInternalErrorResponse(apiResponse);
        }
        return apiResponse;
    }

    @RequestMapping(value = "/v1/page/scenes", method = RequestMethod.GET)
    public ApiResponse getScenesOnPage(ApiRequest apiRequest) {
        logger.info("start to get scenes on page");
        ApiResponse apiResponse = new ApiResponse();
        PageRequest pageRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), PageRequest.class);
        //判断json格式参数是否有误
        if (pageRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }
        int pageType = pageRequest.getPageType();

        try {
            PageSceneResponse pageSceneResponse = pageConfigService.getScenesByPageType(pageType);
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(pageSceneResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, pageSceneResponse);
            } else {
                apiResponse.setData(pageSceneResponse.getSceneList());
            }
        } catch (Exception e) {
            logger.error("查找Scene错误---" + e);
            setInternalErrorResponse(apiResponse);
        }

        return apiResponse;
    }

    @RequestMapping(value = "/v1/page/topics", method = RequestMethod.GET)
    public ApiResponse getTopicsOnPage(ApiRequest apiRequest) {
        logger.info("start to get topics on page");

        ApiResponse apiResponse = new ApiResponse();
        PageRequest pageRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), PageRequest.class);
        //判断json格式参数是否有误
        if (pageRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }
        int pageType = pageRequest.getPageType();

        try {
            PageTopicResponse pageTopicResponse = pageConfigService.getTopicsByPageType(pageType);
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(pageTopicResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, pageTopicResponse);
            } else {
                apiResponse.setData(pageTopicResponse.getPageTopicVoList());
            }
        } catch (Exception e) {
            logger.error("查找Topic错误---" + e);
            setInternalErrorResponse(apiResponse);
        }

        return apiResponse;
    }
}