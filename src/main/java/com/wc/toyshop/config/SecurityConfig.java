package com.wc.toyshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration // IoC Bean 등록
@EnableWebSecurity // 시큐리티 필터 체인안에 항목들을 관리 시작
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();//form에서 post요청시 주고 받는 토큰 비활성화
		
		http.authorizeRequests()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/test/**").permitAll() //여기만 열어둠
			.antMatchers("/admin/**").access("ROLE_ADMIN") //여기는 권한이 있어야함
			.anyRequest().denyAll() //모두 닫음
			;
	}
}
