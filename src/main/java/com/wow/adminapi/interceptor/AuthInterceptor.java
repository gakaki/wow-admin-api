package com.wow.adminapi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wow.adminapi.constant.ApiConstant;
import com.wow.user.service.SessionService;

/**
 * 拦截未登录的用户
 */
@Configuration
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
    @Autowired
    private SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        logger.info("AuthInterceptor preHandle:" + request);
        //        ApiResponse apiResponse = new ApiResponse();
        //        String token = getSessionToken(request, response);
        //        byte loginChannel = getLoginChannel(request, response);
        //
        //        try {
        //            //check whether token is valid, by search it from redis or mysql
        //            TokenValidateResponse tokenValidateResponse = sessionService.isValidSessionToken(token,loginChannel);
        //            if (tokenValidateResponse==null || !tokenValidateResponse.isValid()) {
        //                logger.warn("session token is invalid:" + token);
        //                apiResponse.setResCode("10000");
        //                apiResponse.setResMsg(ErrorCodeUtil.getErrorMsg("10000"));
        //                response.setContentType("application/json");
        //                response.getWriter().write(JsonUtil.pojo2Json(apiResponse));
        //                return false;
        //            }
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //            logger.error("查询Token发生错误---" + e);
        //            apiResponse.setResCode(ErrorCodeConstant.INTERNAL_ERROR);
        //            apiResponse.setResMsg(ErrorCodeUtil.getErrorMsg(ErrorCodeConstant.INTERNAL_ERROR));
        //            response.setContentType("application/json");
        //            response.getWriter().write(JsonUtil.pojo2Json(apiResponse));
        //            return false;
        //        }
        return true;
    }

    /**
     * 获取传入的会话Token - 可从header, cookie或者request中获取
     * @param request
     * @param response
     * @return
     */
    private String getSessionToken(HttpServletRequest request, HttpServletResponse response) {

        // 从请求体中获取token
        String token = request.getParameter(ApiConstant.REQUEST_PARAMETER_TOKEN);

        if (token==null) {
            token = "";
        }
        logger.info("session token is:" + token);
        return token;
    }

    /**
     * 获取传入的login channel
     * @param request
     * @param response
     * @return
     */
    private byte getLoginChannel(HttpServletRequest request, HttpServletResponse response) {
        byte loginChannel = Byte.valueOf(request.getParameter(ApiConstant.REQUEST_PARAMETER_CHANNEL));
        return loginChannel;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}