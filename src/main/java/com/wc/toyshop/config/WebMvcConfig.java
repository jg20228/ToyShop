package com.wc.toyshop.config;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wc.toyshop.config.aop.BasketInterceptor;
import com.wc.toyshop.config.auth.LoginUserAnnotation;
import com.wc.toyshop.config.auth.dto.LoginUser;

import lombok.RequiredArgsConstructor;

//web.xml == WebMvcConfigurer
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer{

	private final HttpSession httpSession;	
	
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		
		resolvers.add(new HandlerMethodArgumentResolver() {
			@Override
			public boolean supportsParameter(MethodParameter parameter) {
				boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUserAnnotation.class) !=null;
				boolean isUserClass = LoginUser.class.equals(parameter.getParameterType());
				return  isLoginUserAnnotation && isUserClass;
			}
			@Override
			public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
					NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
				return httpSession.getAttribute("loginUser");
			}
		});
	}

	//장바구니 들어갈때 Interceptor 발동
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(new BasketInterceptor())
		//.addPathPatterns("/basket/list/");
	}
}
