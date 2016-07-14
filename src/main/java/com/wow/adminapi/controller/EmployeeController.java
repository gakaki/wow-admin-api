package com.wow.adminapi.controller;

import com.wow.adminapi.model.Employee;
import com.wow.adminapi.service.EmployeeService;
import com.wow.adminapi.vo.*;
import com.wow.common.request.ApiRequest;
import com.wow.common.response.ApiResponse;
import com.wow.common.util.BeanUtil;
import com.wow.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengzhiqing on 16/7/14.
 */
@RestController
@CrossOrigin(maxAge = 3600)
public class EmployeeController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * 根据ID查找用户信息
     * @param apiRequest
     * @return
     */
    @RequestMapping(value = "/v1/employee", method = RequestMethod.GET)
    public ApiResponse getEmployeeById(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();
        EmployeeQueryRequest employeeQueryRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), EmployeeQueryRequest.class);
        //判断json格式参数是否有误
        if (employeeQueryRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }
        //hibernate validator
        int employeeId = employeeQueryRequest.getEmployeeId();

        logger.info("根据ID查询用户:" + employeeId);
        try {
            EmployeeResponse employeeResponse = employeeService.getEmployeeById(employeeId);
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(employeeResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, employeeResponse);
            } else {
                apiResponse.setData(employeeResponse.getEmployee());
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
    @RequestMapping(value = "/v1/employee", method = RequestMethod.POST)
    public ApiResponse register(ApiRequest apiRequest) {

        ApiResponse apiResponse = new ApiResponse();

        RegisterRequest registerRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), RegisterRequest.class);
        //判断json格式参数是否有误
        if (registerRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        try {
            RegisterResponse registerResponse = employeeService.register(registerRequest);
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(registerResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, registerResponse);
            } else {
                apiResponse.setData(registerResponse.getEmployeeId());
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
    @RequestMapping(value = "/v1/employee/change", method = RequestMethod.POST)
    public ApiResponse updateEmployee(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();
        logger.info("paramJson=" + apiRequest.getParamJson());
        EmployeeUpdateRequest employeeUpdateRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), EmployeeUpdateRequest.class);
        //判断json格式参数是否有误
        if (employeeUpdateRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }
        Employee employee = new Employee();
        BeanUtil.copyProperties(employeeUpdateRequest,employee);
        try {
            EmployeeUpdateResponse employeeUpdateResponse = employeeService.updateEmployee(employee);
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(employeeUpdateResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, employeeUpdateResponse);
            } else {
                apiResponse.setData(employeeUpdateResponse.isSuccess());
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
    @RequestMapping(value = "/v1/employee/is-mobile-existed", method = RequestMethod.GET)
    public ApiResponse isExistedEmployeeByMobile(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();
        EmployeeCheckRequest employeeCheckRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), EmployeeCheckRequest.class);
        //判断json格式参数是否有误
        if (employeeCheckRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        logger.info("检查是否存在指定手机号的用户:" + employeeCheckRequest.getMobile());

        try {
            EmployeeCheckResponse employeeCheckResponse = employeeService.isExistedEmployeeByMobile(employeeCheckRequest.getMobile());
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(employeeCheckResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, employeeCheckResponse);
            } else {
                apiResponse.setData(employeeCheckResponse.isExistedEmployee());
            }
        } catch (Exception e) {
            logger.error("根据手机号判断用户是否存在发生错误---" + e);
            setInternalErrorResponse(apiResponse);
        }
        return apiResponse;
    }

    @RequestMapping(value = "/v1/employee/is-username-existed", method = RequestMethod.GET)
    public ApiResponse isExistedEmployeeByUserName(ApiRequest apiRequest) {
        ApiResponse apiResponse = new ApiResponse();
        EmployeeCheckRequest employeeCheckRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), EmployeeCheckRequest.class);
        //判断json格式参数是否有误
        if (employeeCheckRequest == null) {
            setParamJsonParseErrorResponse(apiResponse);
            return apiResponse;
        }

        try {
            EmployeeCheckResponse employeeCheckResponse = employeeService.isExistedEmployeeByUserName(employeeCheckRequest.getUserName());
            //如果处理失败 则返回错误信息
            if (!isServiceCallSuccess(employeeCheckResponse.getResCode())) {
                setServiceErrorResponse(apiResponse, employeeCheckResponse);
            } else {
                apiResponse.setData(employeeCheckResponse.isExistedEmployee());
            }
        } catch (Exception e) {
            logger.error("根据用户名判断用户是否存在发生错误---" + e);
            setInternalErrorResponse(apiResponse);
        }

        return apiResponse;
    }

//    @RequestMapping(value = "/v1/employee/captcha", method = RequestMethod.POST)
//    public ApiResponse requestCaptcha(ApiRequest apiRequest) {
//
//        ApiResponse apiResponse = new ApiResponse();
//        CaptchaRequest captchaRequest = JsonUtil.fromJSON(apiRequest.getParamJson(), CaptchaRequest.class);
//        //判断json格式参数是否有误
//        if (captchaRequest == null) {
//            setParamJsonParseErrorResponse(apiResponse);
//            return apiResponse;
//        }
//
//        try {
//            CaptchaResponse captchaResponse = employeeService.sendCaptcha(captchaRequest.getMobile());
//            //如果处理失败 则返回错误信息
//            if (!isServiceCallSuccess(captchaResponse.getResCode())) {
//                setServiceErrorResponse(apiResponse, captchaResponse);
//            } else {
//                apiResponse.setData(captchaResponse.getCaptcha());
//            }
//        } catch (Exception e) {
//            logger.error("发送验证码发生错误---" + e);
//            setInternalErrorResponse(apiResponse);
//        }
//
//        return apiResponse;
//    }

}
