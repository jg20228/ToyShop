package com.wc.toyshop.test;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wc.toyshop.config.auth.PrincipalDetails;

@Controller
public class TestController {
	
	
	@GetMapping("/test/login")
	public @ResponseBody String testLogin(
			Authentication authentication,
			@AuthenticationPrincipal PrincipalDetails userDetails) {
		//DI디펜던시 인젝션 (의존성 주입)
		System.out.println("/test/login ===================");
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		System.out.println("authentication : "+principalDetails.getUser());
		
		System.out.println("userDetails :"+userDetails.getUser());
		return "세션 정보 확인하기";
	}
	
	@GetMapping("/test/oauth/login")
	public @ResponseBody String testOAuthLogin(
			Authentication authentication,
			@AuthenticationPrincipal OAuth2User oAuth) {
		//DI디펜던시 인젝션 (의존성 주입)
		System.out.println("/test/login ===================");
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
		System.out.println("authentication : "+oAuth2User.getAttributes());
		System.out.println("oAuth"+oAuth.getAttributes());
		
		return "oAuth2User 세션 정보 확인하기";
	}
	
	@GetMapping("/test/user")
	public @ResponseBody String testUser(@AuthenticationPrincipal
			PrincipalDetails principalDetails) {
		System.out.println("principalDetails"+principalDetails.getUser());
		return "user";
	}

	
	@GetMapping("/test/index")
	public String testIndex() {
		return "index";
	}
	
	//특정 메소드에 걸때 사용
	@Secured("ROLE_ADMIN")
	@GetMapping("/admin/index")
	public @ResponseBody String adminTest() {
		System.out.println("adminTest");
		return "어드민테스트";
	}
}
