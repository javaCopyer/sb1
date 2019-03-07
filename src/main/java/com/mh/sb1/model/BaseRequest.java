package com.mh.sb1.model;

import java.util.Map;

public class BaseRequest {
	 private String userName;
	 private Map<String, String> headMap;
	 public String getUserName() {
		return userName;
	}

	 public void setUserName(String userName) {
		this.userName = userName;
	}

	 public Map<String, String> getHeadMap() {
		return headMap;
	}

	 public void setHeadMap(Map<String, String> headMap) {
		this.headMap = headMap;
	}
	
}
