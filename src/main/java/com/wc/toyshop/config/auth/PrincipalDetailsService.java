package com.wc.toyshop.config.auth;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wc.toyshop.config.auth.dto.LoginUser;
import com.wc.toyshop.model.User;
import com.wc.toyshop.repository.UserRepository;

import lombok.RequiredArgsConstructor;

// 규칙
// 시큐리티 설정에서 loginProcessingUrl을 걸어뒀기 때문에
// 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadUserByUsername가 실행됨
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	private final HttpSession session;
	
	//시큐리티 session(내부 Authentication(내부 UserDetails))
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//시큐리티 로그인이 실행되면 username으로 if로 분기 나눔
		User userEntity = userRepository.findByUsername(username).get();
		if(userEntity != null) {
			//아이디가 있으면 세션등록 loginUser
			session.setAttribute("loginUser", new LoginUser(userEntity));
		}
		//없으면 새로 만듬
		return new PrincipalDetails(userEntity);
	}
}
