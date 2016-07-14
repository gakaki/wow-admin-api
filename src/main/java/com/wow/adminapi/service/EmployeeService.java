package com.wow.adminapi.service;

import com.wow.adminapi.model.Employee;
import com.wow.adminapi.vo.*;

import java.util.List;

/**
 * Created by zhengzhiqing on 16/7/14.
 */
public interface EmployeeService {

    /**
     * 用户注册
     *
     * @param registerRequest
     * @return
     */
    public RegisterResponse register(RegisterRequest registerRequest);

    /**
     * 根据用户名判断是否已注册用户
     *
     * @param userName
     * @return
     */
    public EmployeeCheckResponse isExistedEmployeeByUserName(String userName);

    /**
     * 根据手机号判断是否已注册用户
     *
     * @param mobile
     * @return
     */
    public EmployeeCheckResponse isExistedEmployeeByMobile(String mobile);


    /**
     * 用户信息更新
     *
     * @param employee
     * @return
     */
    public EmployeeUpdateResponse updateEmployee(Employee employee);

    /**

     /**
     * 用户忘记密码/重置密码 - 需要手机验证(以后可支持邮件验证?)
     *
     * @param mobile
     * @param captcha
     * @param newPwd
     * @return
     */
    public ResetPwdResponse resetPassword(String mobile, String captcha, String newPwd);

    /**
     * 根据Id获取用户信息
     *
     * @param employeeId
     * @return
     */
    public EmployeeResponse getEmployeeById(int employeeId);

    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    public EmployeeResponse getEmployeeByUserName(String userName);

    /**
     * 根据手机号获取用户信息
     *
     * @param mobile
     * @return
     */
    public EmployeeResponse getEmployeeByMobile(String mobile);

    /**
     * 批量查询多个用户
     * 一般是运营后台调用
     *
     * @param employeeIds
     * @return
     */
    public List<Employee> getEmployeesByIds(int[] employeeIds);

    /**
     * 验证手机号(也是用户名)、密码是否匹配
     *
     * @param userName
     * @param password
     * @return
     */
    public EmployeeResponse authenticate(String userName, String password);
}

