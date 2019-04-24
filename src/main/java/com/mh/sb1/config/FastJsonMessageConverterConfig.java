package com.mh.sb1.config;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
@Configuration
public class FastJsonMessageConverterConfig {
	/**
	 * QuoteFieldNames———-输出key时是否使用双引号,默认为true
	 * WriteMapNullValue——–是否输出值为null的字段,默认为false
	 * WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
	 * WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
	 * WriteNullStringAsEmpty—字符类型字段如果为null,输出为"",而非null
	 * WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
	 * WriteDateUseDateFormat全局修改日期格式,默认为false。 PrettyFormat结果是否格式化,默认为false
	 * DisableCircularReferenceDetect 关闭重复引用检测
	 */
	
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverter(FastJsonConfig config) {
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		// 设置字符集 解决乱码问题
		converter.setSupportedMediaTypes(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON_UTF8 }));
		converter.setFastJsonConfig(config);
		return new HttpMessageConverters(converter);
	}
	
	@Bean
	public FastJsonConfig setFastJsonConfig() {
		FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullListAsEmpty);
		return config;
	}
}
