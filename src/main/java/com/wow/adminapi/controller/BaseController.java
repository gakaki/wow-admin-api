package com.wow.adminapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wow.adminapi.constant.ErrorCodeConstant;
import com.wow.common.request.ApiRequest;
import com.wow.common.response.ApiResponse;
import com.wow.common.response.CommonResponse;
import com.wow.common.util.ErrorCodeUtil;
import com.wow.user.service.SessionService;
import com.wow.user.vo.response.TokenValidateResponse;

/**
 * controller基类 用以处理controller中的一些通用方法
 * 
 * @author chenkaiwei
 * @version $Id: V1.0 2016年7月12日 上午10:56:17 Exp $
 */
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private SessionService sessionService;

    /**
     * 
     * 判断业务调用方法是否成功
     * @param errorCode
     * @return
     */
    public boolean isServiceCallSuccess(String errorCode) {
        return "0".equals(errorCode);
    }

    /**
     * 设置具体的业务错误信息
     * 
     * @param apiResponse
     * @param commonResponse
     */
    public void setServiceErrorResponse(ApiResponse apiResponse, CommonResponse commonResponse) {
        apiResponse.setResCode(commonResponse.getResCode());
        apiResponse.setResMsg(commonResponse.getResMsg());
    }

    /**
     * 设置具体的校验出错信息
     * 
     * @param apiResponse
     * @param errorMsg
     */
    public void setInvalidParameterResponse(ApiResponse apiResponse, String errorMsg) {
        apiResponse.setResCode(ErrorCodeConstant.INVALID_PARAMETER);
        apiResponse.setResMsg(errorMsg);
    }

    /**
     * 设置json参数解析错误信息
     * 
     * @param apiResponse
     */
    public void setParamJsonParseErrorResponse(ApiResponse apiResponse) {
        apiResponse.setResCode(ErrorCodeConstant.INVALID_PARAMJSON);
        apiResponse.setResMsg(ErrorCodeUtil.getErrorMsg(ErrorCodeConstant.INVALID_PARAMJSON));
    }

    /**
     * 设置服务器内部错误信息
     *
     * @param apiResponse
     */
    public void setInternalErrorResponse(ApiResponse apiResponse) {
        apiResponse.setResCode(ErrorCodeConstant.INTERNAL_ERROR);
        apiResponse.setResMsg(ErrorCodeUtil.getErrorMsg(ErrorCodeConstant.INTERNAL_ERROR));
    }

    /**
     * 移除响应类中的重复错误码和错误信息
     *
     * @param commonResponse
     */
    public void removeDuplicateResponse(CommonResponse commonResponse) {
        commonResponse.setResCode(null);
        commonResponse.setResMsg(null);
    }

    /**
     * 根据token和channel
     * @param request
     * @return 如果session依然有效,返回对应的end_user_id,否则返回null
     */
    public Integer getUserIdByTokenChannel(ApiRequest request) {
        Integer endUserId = null;
        try {
            String token = request.getSessionToken();
            byte channel = request.getChannel();
            //check whether token is valid, by search it from redis or mysql
            TokenValidateResponse tokenValidateResponse = sessionService.isValidSessionToken(token, channel);
            if (tokenValidateResponse == null || !tokenValidateResponse.isValid()) {
                logger.warn("session token is invalid:" + token);
            } else {
                endUserId = tokenValidateResponse.getEndUserId();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询Token发生错误---" + e);
        }
        return endUserId;
    }

}
