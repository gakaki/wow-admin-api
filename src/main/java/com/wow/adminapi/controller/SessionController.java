package com.wow.adminapi.controller;

import com.wow.adminapi.dto.ApiResponse;
import com.wow.adminapi.util.ErrorRespUtil;
import com.wow.user.model.EndUserSession;
import com.wow.user.service.SessionService;
import com.wow.user.vo.LoginRequest;
import com.wow.user.vo.LoginResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 登录、登出
 * Created by zhengzhiqing on 16/6/23.
 */
@RestController
@RequestMapping("/v1.0/sessions")
public class SessionController {

    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private SessionService sessionService;

    /**
     * 创建新的会话(登入)
     * @param loginRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ApiResponse login(@Validated @RequestBody LoginRequest loginRequest) {
        ApiResponse apiResponse = new ApiResponse();
        logger.info("user login...");
        LoginResult loginResult = sessionService.login(loginRequest);
        if (loginResult.isValidUser()) {
            apiResponse.setResCode("0");
            apiResponse.setResMsg("success");
            apiResponse.setData(loginResult.getEndUserSession());
        } else {
            ErrorRespUtil.setError(apiResponse,"40201");
            apiResponse.setData(loginResult.getEndUserSession());
        }

        return apiResponse;
    }

    /**
     * 销毁当前会话(登出)
     * @param endUserId
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ApiResponse logout(@PathVariable Integer endUserId, @PathVariable byte loginChannel) {
        ApiResponse apiResponse = new ApiResponse();
        logger.info("user logout...");
        int i = sessionService.logout(endUserId,loginChannel);
        if (i > 0) {
            apiResponse.setResCode("0");
            apiResponse.setResMsg("success");
            apiResponse.setData("log out successfully");
        } else {
            apiResponse.setResCode("0");
            apiResponse.setResMsg("failed");
            apiResponse.setData("log out failed");
        }
        return apiResponse;
    }
}
