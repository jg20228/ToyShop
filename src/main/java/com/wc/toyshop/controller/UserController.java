package com.wc.toyshop.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wc.toyshop.controller.dto.UserJoinReqDto;
import com.wc.toyshop.controller.respdto.CommonRespDto;
import com.wc.toyshop.model.KakaoProfile;
import com.wc.toyshop.model.OAuthToken;
import com.wc.toyshop.model.User;
import com.wc.toyshop.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	
	private final AuthenticationManager authenticationManager;
	private final UserService userService;

	
	@Value("${wc.key}")
	private String wcKey;
	
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) {
		
		//여기서 POST 방식으로 key=value 데이터를 요청 (카카오쪽으로)
		//HttpsURLConnection url;
		//OkHttp
		//Retrofit2(라이브러리) - 안드로이드
		//RestTemplate
		
		RestTemplate rt = new RestTemplate();
		
		//HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "25ed0bbfe75444dcca1ac7386b6481cc");
		params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
		params.add("code", code);
		
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		//exchange라는 함수가 httpEntity라는 오브젝트를 받기로 되어있음
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
				new HttpEntity<>(params,headers);
		
		//Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
		);
		
		//응답받은 데이터를 오브젝트에 쏙 넣음
		//Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oaAuthToken = null;
		try {
			oaAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("카카오 엑세스 토큰 : "+oaAuthToken.getAccess_token());
		
		
		//---------------------------------
		RestTemplate rt2 = new RestTemplate();
		//HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oaAuthToken.getAccess_token());
		headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		//exchange라는 함수가 httpEntity라는 오브젝트를 받기로 되어있음
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
				new HttpEntity<>(headers2);
		
		//Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음.
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest,
				String.class
		);
		
		//응답받은 데이터를 오브젝트에 쏙 넣음
		//Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("카카오 아이디(번호)"+kakaoProfile.getId());
		System.out.println("카카오 이메일"+kakaoProfile.getKakao_account().getEmail());
		
		System.out.println("toyShop 유저네임 : "+
		kakaoProfile.getKakao_account().getEmail()
		+"_"
		+kakaoProfile.getId());
		System.out.println("toyShop 이메일"+kakaoProfile.getKakao_account().getEmail());
		
		User kakaoUser = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.email(kakaoProfile.getKakao_account().getEmail())
				.password(wcKey)
				.name("이름")
				.address("입력필요")
				.phone("입력필요")
				.gender("성별")
				.role("ROLE_USER")
				.build();
		
		//이미 가입된 사람인지 비가입자인지 체크를 해서 처리
		User originUser = userService.회원찾기(kakaoUser.getUsername());
		System.out.println(originUser);
		if(originUser.getUsername()==null) {
			System.out.println("기존회원아님");
			userService.회원가입(kakaoUser);
		}
		System.out.println("자동 로그인");
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), wcKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/test/index";
	}
	
	//로그인하는곳
	@GetMapping("/auth/login")
	public String login() {
		System.out.println("login");
		return "/auth/login";
	}
	
	//회원가입하는곳
	@GetMapping("/auth/join")
	public String join() {
		System.out.println("join");
		return "/auth/join";
	}
	
	//회원가입 (수정필요)
	@PostMapping("/auth/join")
	public CommonRespDto<?> joinProc(UserJoinReqDto reqUser) {
		
		User userEntity = User.builder()
				.username(reqUser.getUsername())
				.email(reqUser.getEmail())
				.password(reqUser.getPassword())
				.name("이름")
				.address("입력필요")
				.phone("입력필요")
				.gender("성별")
				.role("ROLE_USER")
				.build();
		
		userService.회원가입(userEntity);
		return new CommonRespDto<String>(1, "회원가입성공");
	}
}
