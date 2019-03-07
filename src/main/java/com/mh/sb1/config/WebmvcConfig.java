package com.mh.sb1.config;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mh.sb1.interceptor.HelloInterceptor;
import com.mh.sb1.resolver.HeadArgumentResolver;

@Configuration
public class WebmvcConfig implements WebMvcConfigurer{
	@Resource
	private HelloInterceptor helloInterceptor;
	@Resource
	private HeadArgumentResolver headArgumentResolver;
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/xx").setViewName("xx");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(helloInterceptor).addPathPatterns("/**");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(headArgumentResolver);
		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
	}
	
	

	

}
