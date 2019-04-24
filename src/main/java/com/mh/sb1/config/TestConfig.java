package com.mh.sb1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mh.sb1.test.TestBean;
import com.mh.sb1.test.TestBean123;

@Configuration
public class TestConfig {

	@Bean
	public TestBean tb111() {
		TestBean tb = new TestBean();
		tb.setName("tb123");
		return tb;
	}
	
	@Bean
	public TestBean123 tb321(TestBean tb) {
		TestBean123 tb123 = new TestBean123(tb);
		return tb123;
	}
}
