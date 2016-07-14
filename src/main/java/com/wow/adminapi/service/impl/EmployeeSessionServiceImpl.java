package com.wow.adminapi.service.impl;

import com.wow.adminapi.mapper.EmployeeLoginLogMapper;
import com.wow.adminapi.mapper.EmployeeSessionMapper;
import com.wow.adminapi.model.*;
import com.wow.adminapi.service.EmployeeService;
import com.wow.adminapi.service.EmployeeSessionService;
import com.wow.adminapi.vo.EmployeeResponse;
import com.wow.adminapi.vo.LoginRequest;
import com.wow.adminapi.vo.LoginResponse;
import com.wow.adminapi.vo.LogoutResponse;
import com.wow.common.util.ErrorCodeUtil;
import com.wow.user.util.IpConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhengzhiqing on 16/6/16.
 */
@Service
@Transactional("adminTransactionManager")
public class EmployeeSessionServiceImpl implements EmployeeSessionService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeSessionServiceImpl.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeSessionMapper employeeSessionMapper;

    @Autowired
    EmployeeLoginLogMapper employeeLoginLogMapper;

    @Value("${session.expirationTime}")
    private long sessionExpirationTime;


    /**
     * 用户登录
     *
     * @param loginRequest
     * @return
     */
    public LoginResponse login(LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        EmployeeSession employeeSession = null;
        //先检查数据库,看用户名和密码是否匹配
        EmployeeResponse employeeResponse = employeeService.authenticate(loginRequest.getUserName(), loginRequest.getPassword());
        Employee employee = employeeResponse.getEmployee();
        if (employee != null) { //验证成功
            //根据employeeId查找EmployeeSession,如果有则更新,没有则创建
            employeeSession = getSessionByEmployeeId(employee.getId());
            long loginIp = IpConvertUtil.ipToLong(loginRequest.getLoginIp());
            Date now = new Date();
            //token生成算法,暂用UUID,可以替换
            String sessionToken = UUID.randomUUID().toString();
            if (employeeSession == null) {
                employeeSession = new EmployeeSession();
                employeeSession.setEmployeeId(employee.getId());
                employeeSession.setIsLogout(false);
                employeeSession.setLastLoginIp(loginIp);
                employeeSession.setLastRefreshTime(now);
                employeeSession.setLastLoginTime(now);
                employeeSession.setLogoutTime(null);
                employeeSession.setSessionToken(sessionToken);
                employeeSession.setUserAgentInfo(loginRequest.getUserAgent());
                employeeSessionMapper.insertSelective(employeeSession);
            } else {
                employeeSession.setIsLogout(false);
                employeeSession.setLastLoginIp(loginIp);
                employeeSession.setLastRefreshTime(now);
                employeeSession.setLastLoginTime(now);
                employeeSession.setLogoutTime(null);
                employeeSession.setSessionToken(sessionToken);
                employeeSession.setUserAgentInfo(loginRequest.getUserAgent());
                //此处不用updateByPrimaryKeySelective,因为setLogoutTime(null)
                employeeSessionMapper.updateByPrimaryKey(employeeSession);
            }

            //记录登录日志
            EmployeeLoginLog employeeLoginLog = new EmployeeLoginLog();
            employeeLoginLog.setUserAgentInfo(loginRequest.getUserAgent());
            employeeLoginLog.setEmployeeId(employee.getId());
            employeeLoginLog.setLoginIp(loginIp);
            employeeLoginLog.setLoginTime(now);
            employeeLoginLog.setSessionToken(sessionToken);
            employeeLoginLogMapper.insert(employeeLoginLog);

//            loginResponse.setUserName(loginRequest.getUserName());
//            loginResponse.setValidUser(true);
//            loginResponse.setErrorMsg(null);
            loginResponse.setEmployeeSession(employeeSession);
        } else {

            loginResponse.setResCode("40101");
            loginResponse.setResMsg(ErrorCodeUtil.getErrorMsg("40101"));
//            loginResponse.setUserName(loginRequest.getUserName());
//            loginResponse.setValidUser(false);
//            loginResponse.setErrorMsg("用户名和密码不匹配,请重新输入");
//            loginResponse.setEmployeeSession(null);
        }
        return loginResponse;
    }


    /**
     * 根据userId和登录渠道查询会话
     *
     * @param employeeId
     * @return
     */
    @Transactional(propagation= Propagation.SUPPORTS)
    public EmployeeSession getSessionByEmployeeId(int employeeId) {
        EmployeeSession employeeSession = null;
        EmployeeSessionExample employeeSessionExample = new EmployeeSessionExample();
        EmployeeSessionExample.Criteria criteria = employeeSessionExample.createCriteria();
        criteria.andEmployeeIdEqualTo(employeeId);
        List<EmployeeSession> employeeSessions = employeeSessionMapper.selectByExample(employeeSessionExample);
        if (employeeSessions != null && employeeSessions.size()==1) {
            employeeSession = employeeSessions.get(0);
        } else if (employeeSessions.size() > 1) {
            //异常,不应该有多条记录
        }
        return employeeSession;
    }

    /**
     * 延续会话过期时间
     *
     * @param employeeSession
     * @return
     */
    private int refreshSession(EmployeeSession employeeSession) {
        EmployeeSessionExample employeeSessionExample = new EmployeeSessionExample();
        EmployeeSessionExample.Criteria criteria = employeeSessionExample.createCriteria();
        criteria.andEmployeeIdEqualTo(employeeSession.getEmployeeId());
        criteria.andIsLogoutEqualTo(false);
        criteria.andIsActiveEqualTo(true);
        employeeSession.setLastRefreshTime(new Date());
        return employeeSessionMapper.updateByExampleSelective(employeeSession, employeeSessionExample);
    }

    /**
     * 查询用户登录日志
     *
     * @param employeeId
     * @return
     */
    @Transactional(propagation= Propagation.SUPPORTS)
    public List<EmployeeLoginLog> getLoginLogsByEmployeeId(int employeeId) {
        EmployeeLoginLogExample employeeLoginLogExample = new EmployeeLoginLogExample();
        EmployeeLoginLogExample.Criteria criteria = employeeLoginLogExample.createCriteria();
        criteria.andEmployeeIdEqualTo(employeeId);
        return employeeLoginLogMapper.selectByExample(employeeLoginLogExample);
    }

    /**
     * 用户登出
     *
     * @param employeeId
     * @return
     */
    public LogoutResponse logout(int employeeId) {
        LogoutResponse logoutResponse = new LogoutResponse();
        EmployeeSession employeeSession = new EmployeeSession();
        employeeSession.setIsLogout(true);
        employeeSession.setLogoutTime(new Date());
        EmployeeSessionExample employeeSessionExample = new EmployeeSessionExample();
        EmployeeSessionExample.Criteria criteria = employeeSessionExample.createCriteria();
        criteria.andEmployeeIdEqualTo(employeeId);
        int i = employeeSessionMapper.updateByExampleSelective(employeeSession, employeeSessionExample);
        logoutResponse.setSuccess(i == 1);
        return logoutResponse;
    }

    /**
     * 判断是否有效session
     *
     * @param sessionToken
     * @return
     */
    @Override
    public boolean isValidSessionToken(String sessionToken) {
        logger.info("check whether is valid session token:" + sessionToken);
        boolean isValid = false;
        long currentTime = System.currentTimeMillis();
        Date mustRefreshAfter = new Date(currentTime - sessionExpirationTime);
        EmployeeSession employeeSession = getValidEmployeeSessionBySessionToken(
                sessionToken, mustRefreshAfter);
        logger.info("employeeSession:" + employeeSession);
        if (employeeSession != null && employeeSession.getId() != null) {
            isValid = true;
            //刷新过期时间,不是每次都刷新,仅仅在当前时间与上次刷新时间之间间隔 < 过期时间的25%处刷新
            if (currentTime - employeeSession.getLastRefreshTime().getTime() < sessionExpirationTime*0.25) {
                refreshSession(employeeSession);
            }
        }
        return isValid;
    }

    /**
     * 根据会话token查找当前有效的会话信息
     * @param sessionToken
     * @param mustRefreshAfter
     * @return
     */
    private EmployeeSession getValidEmployeeSessionBySessionToken(String sessionToken, Date mustRefreshAfter) {
        EmployeeSession employeeSession = null;
        EmployeeSessionExample employeeSessionExample = new EmployeeSessionExample();
        EmployeeSessionExample.Criteria criteria = employeeSessionExample.createCriteria();
        criteria.andSessionTokenEqualTo(sessionToken);
        criteria.andIsLogoutEqualTo(false);
        criteria.andIsActiveEqualTo(true);
        criteria.andLastRefreshTimeGreaterThan(mustRefreshAfter);

        List<EmployeeSession> employeeSessions = employeeSessionMapper.selectByExample(employeeSessionExample);

        if (employeeSessions != null && employeeSessions.size()==1) {
            employeeSession = employeeSessions.get(0);
        } else if (employeeSessions.size() > 1) {
            //异常,不应该有多条记录
        }
        return employeeSession;

    }

    /**
     * 使会话失效 - 常用在修改密码之后
     *
     * @param endUserId
     * @return
     */
    @Override
    public int invalidateSessionToken(int endUserId) {
        EmployeeSessionExample employeeSessionExample = new EmployeeSessionExample();
        EmployeeSessionExample.Criteria criteria=employeeSessionExample.createCriteria();
        criteria.andIdEqualTo(endUserId);
        List<EmployeeSession> employeeSessions=getAllActiveUserSession(endUserId);
        for (EmployeeSession employeeSession : employeeSessions) {
            employeeSession.setIsActive(false);
            employeeSessionMapper.updateByExampleSelective(employeeSession, employeeSessionExample);
        }
        return employeeSessions.size();
    }

    /**
     * 获取所有有效的会话
     * @param endUserId
     * @return
     */
    private List<EmployeeSession> getAllActiveUserSession(int endUserId) {
        EmployeeSessionExample employeeSessionExample = new EmployeeSessionExample();
        EmployeeSessionExample.Criteria criteria = employeeSessionExample.createCriteria();
        criteria.andIdEqualTo(endUserId);
        criteria.andIsLogoutEqualTo(false);
        criteria.andIsActiveEqualTo(true);
        return employeeSessionMapper.selectByExample(employeeSessionExample);
    }
}
