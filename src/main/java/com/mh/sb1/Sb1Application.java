package com.mh.sb1;

import javax.annotation.Resource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mh.sb1.config.UserConfig;
import com.mh.sb1.config.UserService;
import com.mh.sb1.config.UserService2;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@RestController
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) 
public class Sb1Application extends SpringBootServletInitializer{
	Logger logger = LoggerFactory.getLogger(Sb1Application.class);
	@Resource
	private UserConfig userConfig;
	@Resource
	private UserService userService;
	@Resource
	private UserService2 userService2;
	@RequestMapping("index")
	public String index() {
		return "你好 this index: " + userConfig.getName();
		
	}
	
	@RequestMapping("bean")
	public String bean() {
		return userService2.getMsg();
		
	}
	@RequestMapping("auto")
	public String auto() {
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
        logger.error("error");
		return userService.say();
	}
	
	
	/**
	 * 外部tomcat启动
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Sb1Application.class);
	}
	
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Sb1Application.class);
		app.setBannerMode(Mode.CONSOLE); //banner开关
		app.run(args);
//		SpringApplication.run(Sb1Application.class, args);
	}

}

