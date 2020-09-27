package com.wc.toyshop.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.wc.toyshop.config.auth.PrincipalDetailsService;
import com.wc.toyshop.config.oauth.PrincipalOauth2UserService;
import com.wc.toyshop.util.Script;


@Configuration // IoC Bean 등록
@EnableWebSecurity // 시큐리티 필터 체인안에 항목들을 관리 시작
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //securedEnabled = secured 어노테이션 활성화
//prePostEnabled @PreAuthorize, postAuthorize 어노테이션 활성화 (hasRole('ROLE_MANAGER'))
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	
	@Autowired
	private PrincipalDetailsService principalDetailService;
	
	//해당 메서드의 리턴되는 오브젝트를 IoC로 등록해줌
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	//패스워드 비교하는곳
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePwd());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();//form에서 post요청시 주고 받는 토큰 비활성화
		
		http.authorizeRequests()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/img/**").permitAll()
			.antMatchers("/auth/**").permitAll()
			.antMatchers("/product/**").permitAll()
			.antMatchers("/basket/**").permitAll()
			.antMatchers("/orders/**").permitAll()
			.antMatchers("/user/**").permitAll()
			.antMatchers("/jusoPopup.jsp").permitAll()
			.antMatchers("/test/**").permitAll() //여기만 열어둠
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") //여기는 권한이 있어야함
			.anyRequest().denyAll() //모두 닫음
		.and()
			.formLogin() 
			.loginPage("/auth/login")
			.loginProcessingUrl("/auth/loginProc") //시큐리티가 낚아채서 대신 로그인 진행함
			.defaultSuccessUrl("/test/index")
			.failureHandler(new AuthenticationFailureHandler() {
				
				@Override
				public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
						AuthenticationException exception) throws IOException, ServletException {
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.print(Script.back("아이디 혹은 비밀번호가 틀립니다."));
					
					//여기서 세션을 무효화 하는게 맞는지 모르겠음
					HttpSession session = request.getSession();
					session.invalidate();
					System.out.println("세션무효화");
					return;
				}
			})
		.and()
			.oauth2Login()
			.loginPage("/auth/login")
			.userInfoEndpoint()
			.userService(principalOauth2UserService)//후처리 : loadUser
			;
		//구글 로그인이 완료되면 코드X (엑세스토큰+사용자프로필정보를 한번에 받음)<-OAuth2 클라이언트 라이브러리
	}
}
