package com.wc.toyshop.config.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.wc.toyshop.config.handler.exception.MyUserIdNotFoundException;
import com.wc.toyshop.util.Script;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = MyUserIdNotFoundException.class)
	public String myUserIdNotFoundException(Exception e) {
		return Script.back(e.getMessage());
	}
}
