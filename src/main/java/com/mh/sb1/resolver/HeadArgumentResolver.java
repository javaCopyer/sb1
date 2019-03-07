package com.mh.sb1.resolver;

import java.io.UnsupportedEncodingException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.mh.sb1.constant.AuthConstant;
import com.mh.sb1.model.BaseRequest;
/**
 * 自定义参数解析器
 * 将请求head参数封装到controller请求对象参数中
 * @author sky
 *
 */
@Component
public class HeadArgumentResolver implements HandlerMethodArgumentResolver{
	private static final Logger logger = LoggerFactory.getLogger(HeadArgumentResolver.class);
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().getSuperclass().equals(BaseRequest.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		//获取controller参数对象名称
		String name = parameter.getParameterName();
		 //拿到实例, 先从ModelAndViewContainer中拿，若没有则new1个参数类型的实例
		Object attribute = mavContainer.containsAttribute(name) ? mavContainer.getModel().get(name)
				: BeanUtils.instantiateClass(parameter.getParameterType());
		// 生成参数绑定器，第一个参数为request请求对象，第二个参数为需要绑定的目标对象，第三个参数为需要绑定的目标对象名
		if (null != binderFactory) {
			 WebDataBinder binder = binderFactory.createBinder(webRequest, attribute, name);
			 if (binder.getTarget() != null) {
				//进行请求body参数绑定
				this.bindRequestParameters(binder, webRequest);
				//进行请求head参数绑定
				bindHeadParameters(binder, webRequest);
			}
			// 将参数转到预期类型，第一个参数为解析后的值，第二个参数为绑定Controller参数的类型，第三个参数为绑定的Controller参数
			attribute = binder.convertIfNecessary(binder.getTarget(), parameter.getParameterType(), parameter);
		}
		return attribute;
		 
	}
	
	private void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) throws UnsupportedEncodingException {
		// 将key-value封装为map，传给bind方法进行参数值绑定
		Map<String, String> map = new HashMap<>();
		Map<String, String[]> params = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
		String name = entry.getKey();
		map.put(name, entry.getValue()[0]);
		}
		PropertyValues propertyValues = new MutablePropertyValues(map);
		 
		// 将K-V绑定到binder.target属性上
		binder.bind(propertyValues);
		}
	
	private void bindHeadParameters(WebDataBinder binder, NativeWebRequest request) throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<>();
		String userName = request.getHeader(AuthConstant.HEADER_USERNAME);
		map.put("userName", userName);
		map.put("headMap", getHeaderMap(request));
		PropertyValues propertyValues = new MutablePropertyValues(map);
		 
		// 将K-V绑定到binder.target属性上
		binder.bind(propertyValues);
		}
	
	private Map<String, String> getHeaderMap(NativeWebRequest request) {
		Map<String, String> headerMap = new HashMap<>();
		if (request.getHeader(AuthConstant.HEADER_PHONE_TYPE) != null) {
			headerMap.put(AuthConstant.HEADER_PHONE_TYPE, request.getHeader(AuthConstant.HEADER_PHONE_TYPE));
		}
		if (request.getHeader(AuthConstant.HEADER_WEB_FLAG) != null) {
			headerMap.put(AuthConstant.HEADER_WEB_FLAG, request.getHeader(AuthConstant.HEADER_WEB_FLAG));
		}
		if (request.getHeader(AuthConstant.HEADER_DEVICE_TYPE) != null) {
			headerMap.put(AuthConstant.HEADER_DEVICE_TYPE, request.getHeader(AuthConstant.HEADER_DEVICE_TYPE));
		}
		if (request.getHeader(AuthConstant.HEADER_PLATFORM_TYPE) != null) {
			headerMap.put(AuthConstant.HEADER_PLATFORM_TYPE, request.getHeader(AuthConstant.HEADER_PLATFORM_TYPE));
		}
		if (request.getHeader(AuthConstant.HEADER_DEVICE_ID) != null) {
			headerMap.put(AuthConstant.HEADER_DEVICE_ID, request.getHeader(AuthConstant.HEADER_DEVICE_ID));
		}
		if (request.getHeader(AuthConstant.HEADER_ACCESSTOKEN) != null) {
			headerMap.put(AuthConstant.HEADER_ACCESSTOKEN, request.getHeader(AuthConstant.HEADER_ACCESSTOKEN));
		}
		if (request.getHeader(AuthConstant.HEADER_UPDATE_PACKAGE) != null) {
			headerMap.put(AuthConstant.HEADER_UPDATE_PACKAGE, request.getHeader(AuthConstant.HEADER_UPDATE_PACKAGE));
		}
		if (request.getHeader(AuthConstant.HEADER_USERNAME) != null) {
			headerMap.put(AuthConstant.HEADER_USERNAME, request.getHeader(AuthConstant.HEADER_USERNAME).trim());
		}
		if (request.getHeader(AuthConstant.HEADER_WEB_DOMAIN) != null) {
			headerMap.put(AuthConstant.HEADER_WEB_DOMAIN, request.getHeader(AuthConstant.HEADER_WEB_DOMAIN));
		}
		
		if (request.getHeader("agent") != null) {
			headerMap.put("userAgent", request.getHeader("user-agent"));
		}
		return headerMap;
	}

}
