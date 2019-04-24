package com.mh.sb1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;

public class RedisConfig {
	@Bean
	public RedisTemplate<String, Object> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory, FastJsonConfig fastJsonConfig) {
		 RedisTemplate<String, Object> template = new RedisTemplate<>();
		 FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
		 fastJsonRedisSerializer.setFastJsonConfig(fastJsonConfig);
		 template.setKeySerializer(new StringRedisSerializer());
		 template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		 template.setConnectionFactory(redisConnectionFactory);
		 return template;
	}
}
