package com.wow.adminapi.vo;

import com.wow.common.request.ApiRequest;

/**
 * Created by zhengzhiqing on 16/7/6.
 */
public class EmployeeQueryRequest extends ApiRequest {
    private int employeeId;
    private String userName;
    private String realName;
    private String mobile;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
