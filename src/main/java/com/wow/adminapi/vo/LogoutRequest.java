package com.wow.adminapi.vo;

import com.wow.common.request.ApiRequest;

/**
 * Created by zhengzhiqing on 16/7/14.
 */
public class LogoutRequest extends ApiRequest {
    /**  */
    private static final long serialVersionUID = 1L;
    private int employeeId;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
