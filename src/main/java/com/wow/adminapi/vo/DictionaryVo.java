package com.wow.adminapi.vo;

import java.io.Serializable;

public class DictionaryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer keyId;

    private String keyValue;

	public Integer getKeyId() {
		return keyId;
	}

	public void setKeyId(Integer keyId) {
		this.keyId = keyId;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

}
