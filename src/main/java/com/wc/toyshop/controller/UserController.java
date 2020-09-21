package com.wc.toyshop.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.wc.toyshop.controller.dto.UserJoinReqDto;
import com.wc.toyshop.controller.respdto.CommonRespDto;
import com.wc.toyshop.model.User;
import com.wc.toyshop.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
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
		
		String rawPassword = reqUser.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		reqUser.setPassword(encPassword);
		
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
		
		userRepository.save(userEntity);
		return new CommonRespDto<String>(1, "회원가입성공");
	}
}
