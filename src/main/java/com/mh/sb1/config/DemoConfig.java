package com.mh.sb1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Configuration
public class DemoConfig {
	@Bean
	public UserService2 userService2() {
		UserService2 us = new UserService2();
		us.setMsg("123");
		return us;
	}
}
