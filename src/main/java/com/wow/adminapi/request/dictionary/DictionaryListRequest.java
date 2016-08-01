package com.wow.adminapi.request.dictionary;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.wow.common.request.ApiRequest;

public class DictionaryListRequest extends ApiRequest {
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private List<String> keyGroups;

	public List<String> getKeyGroups() {
		return keyGroups;
	}

	public void setKeyGroups(List<String> keyGroups) {
		this.keyGroups = keyGroups;
	}
	
}
