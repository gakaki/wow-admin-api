package com.wow.adminapi.controller;

import com.wow.common.request.ApiRequest;
import com.wow.common.response.ApiResponse;
import com.wow.common.util.BeanUtil;
import com.wow.common.util.JsonUtil;
import com.wow.user.model.EndUser;
import com.wow.user.model.EndUserWechat;
import com.wow.user.service.UserService;
import com.wow.user.vo.request.*;
import com.wow.user.vo.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 根据ID查找用户信息
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/user", method = RequestMethod.GET)
    public ApiResponse getEndUserById(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();
        UserQueryRequest userQueryRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), UserQueryRequest.class);
        //判断json格式参数是否有误
        if (userQueryRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }
        //hibernate validator
        int endUserId = userQueryRequest.getEndUserId();

        logger.info("根据ID查询用户:" + endUserId);
        try {
            UserResponse userResponse = userService.getEndUserById(endUserId);
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(userResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, userResponse);
            } else {
                apiResponse.setData(userResponse.getEndUser());
            }
        } catch (Exception e) {
            logger.error("根据ID查找用户错误---" + e);
            setInternalErrorResponse(apiResponse);
        }
        logger.info("根据ID查询用户,返回结果:" + JsonUtil.pojo2Json(apiResponse));
        return apiResponse;
    }

    /**
     * 用户注册(创建新用户)
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/user", method = RequestMethod.POST)
    public ApiResponse register(ApiRequest apiRequest) {

        ApiResponse apiResponse = new ApiResponse();

        RegisterRequest registerRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), RegisterRequest.class);
        //判断json格式参数是否有误
        if (registerRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        try {
            RegisterResponse registerResponse = userService.register(registerRequest);
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(registerResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, registerResponse);
            } else {
                apiResponse.setData(registerResponse.getEndUserId());
            }
        } catch (Exception e) {
            logger.error("注册发生错误---" + e);
            setInternalErrorResponse(apiResponse);
        }
        logger.info("用户注册,返回结果:" + JsonUtil.pojo2Json(apiResponse));
        return apiResponse;
    }

    /**
     * 修改用户信息
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/user/change", method = RequestMethod.POST)
    public ApiResponse updateUser(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();
        logger.info("paramJson=" + apiRequest.getParamJson());
        UserUpdateRequest userUpdateRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), UserUpdateRequest.class);
        //判断json格式参数是否有误
        if (userUpdateRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }
        EndUser endUser = new EndUser();
        BeanUtil.copyProperties(userUpdateRequest,endUser);
        try {
            UserUpdateResponse userUpdateResponse = userService.updateEndUser(endUser);
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(userUpdateResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, userUpdateResponse);
            } else {
                apiResponse.setData(userUpdateResponse.isSuccess());
            }
        } catch (Exception e) {
            logger.error("修改用户发生错误---" + e);
            setInternalErrorResponse(apiResponse);
        }
        return apiResponse;

    }

    /**
     *
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/user/is-mobile-existed", method = RequestMethod.GET)
    public ApiResponse isExistedUserByMobile(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();
        UserCheckRequest userCheckRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), UserCheckRequest.class);
        //判断json格式参数是否有误
        if (userCheckRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        logger.info("检查是否存在指定手机号的用户:" + userCheckRequest.getMobile());

        try {
            UserCheckResponse userCheckResponse = userService.isExistedUserByMobile(userCheckRequest.getMobile());
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(userCheckResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, userCheckResponse);
            } else {
                apiResponse.setData(userCheckResponse.isExistedUser());
            }
        } catch (Exception e) {
            logger.error("根据手机号判断用户是否存在发生错误---" + e);
            setInternalErrorResponse(apiResponse);
        }
        return apiResponse;
    }

    @RequestMapping(value = "/v1/user/is-nickname-existed", method = RequestMethod.GET)
    public ApiResponse isExistedUserByNickName(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();
        UserCheckRequest userCheckRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), UserCheckRequest.class);
        //判断json格式参数是否有误
        if (userCheckRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        try {
            UserCheckResponse userCheckResponse = userService.isExistedUserByNickName(userCheckRequest.getNickName());
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(userCheckResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, userCheckResponse);
            } else {
                apiResponse.setData(userCheckResponse.isExistedUser());
            }
        } catch (Exception e) {
            logger.error("根据昵称判断用户是否存在发生错误---" + e);
            setInternalErrorResponse(apiResponse);
        }

        return apiResponse;
    }

    @RequestMapping(value = "/v1/user/captcha", method = RequestMethod.POST)
    public ApiResponse requestCaptcha(ApiRequest apiRequest) {

        ApiResponse apiResponse = new ApiResponse();
        CaptchaRequest captchaRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), CaptchaRequest.class);
        //判断json格式参数是否有误
        if (captchaRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        try {
            CaptchaResponse captchaResponse = userService.sendCaptcha(captchaRequest.getMobile());
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(captchaResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, captchaResponse);
            } else {
                apiResponse.setData(captchaResponse.getCaptcha());
            }
        } catch (Exception e) {
            logger.error("发送验证码发生错误---" + e);
            setInternalErrorResponse(apiResponse);
        }

        return apiResponse;
    }

    /**
     * 检查是否绑定微信
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/user/wechat-bind-status", method = RequestMethod.GET)
    public ApiResponse checkWechatBindStatus(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();
        WechatBindQueryRequest wechatBindQueryRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), WechatBindQueryRequest.class);
        //判断json格式参数是否有误
        if (wechatBindQueryRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        try {
            WechatBindStatusResponse wechatBindStatusResponse = userService.checkWechatBindStatus(wechatBindQueryRequest.getMobile());
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(wechatBindStatusResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, wechatBindStatusResponse);
            } else {
                apiResponse.setData(wechatBindStatusResponse.getWechatBindStatusVo());
            }
        } catch (Exception e) {
            logger.error("发送验证码发生错误---" + e);
            setInternalErrorResponse(apiResponse);
        }

        return apiResponse;
    }

    /**
     * 绑定微信
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/user/wechat-bind", method = RequestMethod.POST)
    public ApiResponse bindWechat(ApiRequest apiRequest) {

        ApiResponse apiResponse = new ApiResponse();
        WechatBindRequest wechatBindRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), WechatBindRequest.class);
        //判断json格式参数是否有误
        if (wechatBindRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }
        EndUserWechat endUserWechat = new EndUserWechat();
        BeanUtil.copyProperties(wechatBindRequest,endUserWechat);
        try {
            WechatBindStatusResponse wechatBindStatusResponse = userService.bindWechatToUser(endUserWechat);
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(wechatBindStatusResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, wechatBindStatusResponse);
            } else {
                apiResponse.setData(wechatBindStatusResponse);
            }
        } catch (Exception e) {
            logger.error("发送验证码发生错误---" + e);
            setInternalErrorResponse(apiResponse);
        }

        return apiResponse;

    }

}