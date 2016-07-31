package com.wow.adminapi.response.area;

import com.wow.common.response.CommonResponse;
import com.wow.user.model.Area;

import java.util.List;

public class AreaResponse extends CommonResponse {

	private static final long serialVersionUID = 1L;
	
	private List<Area> areaList;

	public List<Area> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}
	
	
}
