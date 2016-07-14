package com.wow.adminapi.vo;

import com.wow.adminapi.model.Employee;
import com.wow.common.response.CommonResponse;

/**
 * Created by zhengzhiqing on 16/7/14.
 */
public class EmployeeResponse extends CommonResponse {
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
