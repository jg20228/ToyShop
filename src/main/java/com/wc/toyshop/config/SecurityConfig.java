package com.wc.toyshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration // IoC Bean 등록
@EnableWebSecurity // 시큐리티 필터 체인안에 항목들을 관리 시작
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //securedEnabled = secured 어노테이션 활성화
//prePostEnabled @PreAuthorize, postAuthorize 어노테이션 활성화 (hasRole('ROLE_MANAGER'))
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	//해당 메서드의 리턴되는 오브젝트를 IoC로 등록해줌
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();//form에서 post요청시 주고 받는 토큰 비활성화
		
		http.authorizeRequests()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/img/**").permitAll()
			.antMatchers("/auth/**").permitAll()
			.antMatchers("/test/**").permitAll() //여기만 열어둠
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") //여기는 권한이 있어야함
			.anyRequest().denyAll() //모두 닫음
		.and()
			.formLogin() 
			.loginPage("/auth/login")
			.loginProcessingUrl("/auth/loginProc") //시큐리티가 낚아채서 대신 로그인 진행함
			.defaultSuccessUrl("/test/index")
			;
	}
}
