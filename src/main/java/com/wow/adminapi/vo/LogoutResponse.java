package com.wow.adminapi.vo;

import com.wow.common.response.CommonResponse;

/**
 * Created by zhengzhiqing on 16/7/14.
 */
public class LogoutResponse extends CommonResponse {

    /**  */
    private static final long serialVersionUID = 1L;
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
