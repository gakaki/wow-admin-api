package com.wow.adminapi.vo;

import com.wow.common.request.ApiRequest;

/**
 * Created by zhengzhiqing on 16/7/14.
 */
public class EmployeeCheckRequest extends ApiRequest {

    /**  */
    private static final long serialVersionUID = 1L;

    private String userName;

    private String realName;

    private String nickName;

    private String mobile;

    private String email;

    private Integer employeeNo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Integer employeeNo) {
        this.employeeNo = employeeNo;
    }
}
