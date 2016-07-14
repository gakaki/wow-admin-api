package com.wow.adminapi.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wow.adminapi.model.EmployeeSession;
import com.wow.common.response.CommonResponse;
import com.wow.user.model.EndUserSession;

/**
 * Created by zhengzhiqing on 16/7/14.
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class LoginResponse extends CommonResponse {
    private EmployeeSession employeeSession;

    public EmployeeSession getEmployeeSession() {
        return employeeSession;
    }

    public void setEmployeeSession(EmployeeSession employeeSession) {
        this.employeeSession = employeeSession;
    }
}
