package com.wow.adminapi.response.dictionary;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wow.adminapi.vo.DictionaryVo;
import com.wow.common.response.CommonResponse;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class DictionaryResponse extends CommonResponse {

	private static final long serialVersionUID = 1L;
	
	private Map<String,List<DictionaryVo>> dictionaryList;

	public Map<String, List<DictionaryVo>> getDictionaryList() {
		return dictionaryList;
	}

	public void setDictionaryList(Map<String, List<DictionaryVo>> dictionaryList) {
		this.dictionaryList = dictionaryList;
	}
	
	
}
