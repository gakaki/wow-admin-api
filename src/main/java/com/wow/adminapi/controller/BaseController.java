package com.wow.adminapi.controller;

import com.wow.adminapi.constant.ErrorCodeConstant;
import com.wow.common.response.ApiResponse;
import com.wow.common.response.CommonResponse;
import com.wow.common.util.ErrorCodeUtil;

/**
 * controller基类 用以处理controller中的一些通用方法
 * 
 * @author chenkaiwei
 * @version $Id: V1.0 2016年7月12日 上午10:56:17 Exp $
 */
public class BaseController {

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

}
