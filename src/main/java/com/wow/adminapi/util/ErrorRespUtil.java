package com.wow.adminapi.util;

import com.wow.adminapi.dto.ApiResponse;
import com.wow.adminapi.exception.ApiErrorCode;

/**
 * Created by zhengzhiqing on 16/6/27.
 */
public class ErrorRespUtil {
    public static void setError(ApiResponse apiResponse, String resCode) {
        apiResponse.setResCode(resCode);
        apiResponse.setResMsg(ApiErrorCode.getErrorMsg(resCode));
    }
}

