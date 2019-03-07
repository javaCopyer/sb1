package com.mh.sb1.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mh.sb1.servlet.HelloServlet;


/**
 * servlet, filter,listener 注册
 * @author sky
 *
 */
@Configuration
public class WebConfig {
	   @Bean
	    public ServletRegistrationBean<HelloServlet> helloServletRegistrationBean() {
	        ServletRegistrationBean<HelloServlet> registration = new ServletRegistrationBean<HelloServlet>(new HelloServlet());
	        registration.addUrlMappings("/helloServlet");
	        return registration;
	    }
}
