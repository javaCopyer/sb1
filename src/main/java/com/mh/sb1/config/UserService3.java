package com.mh.sb1.config;

public class UserService3 {
	private String msg;

	public String say() {
		return "say:" + msg;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
