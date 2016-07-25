package com.wow.adminapi.vo;

import com.wow.common.response.CommonResponse;

/**
 * Created by zhengzhiqing on 16/7/14.
 */
public class ResetPwdResponse extends CommonResponse {
    /**  */
    private static final long serialVersionUID = 1L;
    private boolean isSuccess;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
