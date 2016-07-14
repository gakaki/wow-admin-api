package com.wow.adminapi.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wow.common.response.CommonResponse;

/**
 * Created by zhengzhiqing on 16/7/6.
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RegisterResponse extends CommonResponse {

    private int employeeId;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
