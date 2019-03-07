package com.mh.sb1.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mh.sb1.config.UserService;
import com.mh.sb1.model.CustomRequest;
import com.mh.sb1.model.UserRequest;
import com.mh.sb1.model.UserResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value="api测试")
public class ApiController {
	
	@RequestMapping(value="/testp1", method=RequestMethod.GET)
	@ApiOperation("api测试方法")
	public UserResponse test111(@RequestParam UserRequest request) {
		UserResponse res  = new UserResponse();
		res.setUserName("zc");
		res.setDate(new Date());
		return res;
	}
	
	@RequestMapping(value="/testp2", method=RequestMethod.GET)
	@ApiOperation("api测试方法")
	public UserResponse test222(@RequestParam Map<String, String> map) {
		UserResponse res  = new UserResponse();
		res.setUserName(map.get("na"));
		res.setDate(new Date());
		return res;
	}
	
	@RequestMapping(value="/testp3", method=RequestMethod.GET)
	@ApiOperation("api测试方法")
	public UserResponse test333(CustomRequest request) {
		System.out.println(request.getUserName());
		System.out.println(request.getHeadMap());
		UserResponse res  = new UserResponse();
		res.setUserName(request.getUserName());
		res.setDate(new Date());
		return res;
	}
}
