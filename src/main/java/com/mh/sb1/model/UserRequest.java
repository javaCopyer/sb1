package com.mh.sb1.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户请求")
public class UserRequest {
	@ApiModelProperty(value="用户名", required=true)
//	@ApiParam(name="姓名吗", value="name")
	@NotNull
	private String name;
	@ApiModelProperty(value="手机号")
	@NotNull(message="手机号不能为空")
	private String phone;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}
