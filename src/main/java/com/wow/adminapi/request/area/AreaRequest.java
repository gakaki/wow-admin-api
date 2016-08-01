package com.wow.adminapi.request.area;

import com.wow.common.request.ApiRequest;

public class AreaRequest extends ApiRequest {
	private static final long serialVersionUID = 1L;
	
	private Integer areaId;

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
}
