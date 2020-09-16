package com.wc.toyshop.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wc.toyshop.model.User;
import com.wc.toyshop.repository.UserRepository;

// 규칙
// 시큐리티 설정에서 loginProcessingUrl을 걸어뒀기 때문에
// 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadUserByUsername가 실행됨
@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	//시큐리티 session(내부 Authentication(내부 UserDetails))
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findByUsername(username);
		if(userEntity != null) {
			return new PrincipalDetails(userEntity);
		}
		return null;
	}
}
