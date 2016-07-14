package com.wow.adminapi.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wow.common.response.CommonResponse;

/**
 * Created by zhengzhiqing on 16/7/14.
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class EmployeeUpdateResponse extends CommonResponse {
    private boolean isSuccess;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}

