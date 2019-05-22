package com.mh.sb1.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mh.sb1.model.CustomRequest;
import com.mh.sb1.model.UserRequest;
import com.mh.sb1.model.UserResponse;
import com.mh.sb1.test.TestBean123;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags={"api文档"})
public class ApiController {
	@Resource
	TestBean123 tb123;
	
	@RequestMapping(value="/testp1", method=RequestMethod.GET)
	@ApiOperation("api测试方法")
	public UserResponse test111(UserRequest request, HttpServletRequest req) {
		System.out.println(tb123.tb.getName());
		UserResponse res  = new UserResponse();
		res.setUserName("zcccc");
		
		res.setDate(new Date());
		
		return res;
	}
	
	@RequestMapping(value="/testp2", method=RequestMethod.GET)
	@ApiOperation("api测试方法")
	@ApiImplicitParam(name="name", value="姓名", paramType="query", example="123")
	public UserResponse test222(@ApiIgnore Map<String, String> map) {
		UserResponse res  = new UserResponse();
		res.setUserName(map.get("name"));
		res.setDate(new Date());
		return res;
	}
	
	@RequestMapping(value="/testp3", method=RequestMethod.POST)
	@ApiOperation("api测试方法")
	public UserResponse test333( HttpServletRequest req,CustomRequest request, @RequestBody Map<String, String> map) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String line = null;
		StringBuilder builder = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			
		}
		String resStr = builder.toString();
		System.out.println("聚汇支付响应报文：" + resStr);
		System.out.println(map);
		System.out.println(request.getCn());
		UserResponse res  = new UserResponse();
		res.setUserName(map.get("name"));
		res.setDate(new Date());
		return res;
	}
	
	@RequestMapping(value="/testp4", method=RequestMethod.POST)
	@ApiOperation("api测试方法")
	public UserResponse test44(@RequestParam(value="name1") @ApiParam(name="name", value="姓名") String name) throws IOException {
		UserResponse res  = new UserResponse();
		res.setUserName(name);
		res.setDate(new Date());
		return res;
	}
}
