package com.mh.sb1.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
	@ExceptionHandler(value = Exception.class)
	public void handleGlobalException(HttpServletRequest req, Exception e) {
		e.printStackTrace();
	}
}

