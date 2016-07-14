package com.wow.adminapi.service;


import com.wow.adminapi.model.EmployeeLoginLog;
import com.wow.adminapi.model.EmployeeSession;
import com.wow.adminapi.vo.LoginRequest;
import com.wow.adminapi.vo.LoginResponse;
import com.wow.adminapi.vo.LogoutResponse;

import java.util.List;

/**
 *
 */
public interface EmployeeSessionService {

    /**
     * 用户登录
     *
     * @param loginRequest
     * @return
     */
    LoginResponse login(LoginRequest loginRequest);

    /**
     * 根据employeeId查询会话
     *
     * @param employeeId
     * @return
     */
    EmployeeSession getSessionByEmployeeId(int employeeId);

    /**
     * 查询用户登录日志
     *
     * @param employeeId
     * @return
     */
    List<EmployeeLoginLog> getLoginLogsByEmployeeId(int employeeId);

    /**
     * 用户登出
     *
     * @param employeeId
     * @return
     */
    LogoutResponse logout(int employeeId);

    /**
     * 判断是否有效session
     * @param sessionToken
     * @return
     */
    boolean isValidSessionToken(String sessionToken);

    /**
     * 使会话失效 - 常用在修改密码之后
     * @param employeeId
     * @return
     */
    int invalidateSessionToken(int employeeId);

}
