package com.wow.adminapi.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wow.common.response.CommonResponse;

/**
 * Created by zhengzhiqing on 16/7/12.
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class EmployeeCheckResponse extends CommonResponse {
    /**  */
    private static final long serialVersionUID = 1L;
    private boolean existedEmployee;

    public boolean isExistedEmployee() {
        return existedEmployee;
    }

    public void setExistedEmployee(boolean existedEmployee) {
        this.existedEmployee = existedEmployee;
    }
}
