package com.wow.adminapi.request.area;

import com.wow.common.request.ApiRequest;

/**
 * Created by win7 on 2016/7/28.
 */
public class AreaRequest extends ApiRequest{

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String areaName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
