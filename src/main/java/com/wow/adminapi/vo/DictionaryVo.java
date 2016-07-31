package com.wow.adminapi.vo;

import java.io.Serializable;

public class DictionaryVo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;

    private String keyName;

    private String keyValue;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

}
