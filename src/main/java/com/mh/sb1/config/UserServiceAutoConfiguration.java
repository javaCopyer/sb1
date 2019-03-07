package com.mh.sb1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置类
 * @author sky
 *
 */
@Configuration
//使@ConfigurationProperties在springboot中起作用
@EnableConfigurationProperties(UserConfig.class)
@ConditionalOnClass(UserService.class)
@ConditionalOnProperty(prefix="local.user", value="enabled", matchIfMissing=true)
public class UserServiceAutoConfiguration {
	@Autowired
	private UserConfig userConfig;
	
	@Bean
	public UserService userService() {
		UserService userService = new UserService();
		userService.setMsg(userConfig.getName());
		return userService;
	}
}
