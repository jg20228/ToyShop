package com.wc.toyshop.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.wc.toyshop.model.User;

import lombok.Data;

//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킴
//로그인 진행이 완료가 되면 session을 만들어줍니다. (Security ContextHolder)
//여기에 들어갈수있는 오브젝트가 정해져있음 => Authentication 타입의 객체만 가능

// Authentication 안에 User 정보가 있어야 됨.
// User 오브젝트타입 => UserDetails 타입 객체여야 한다.


//결론 :Security Session => Authentication => UserDetails(PrincipalDetails)

@Data
public class PrincipalDetails implements UserDetails, OAuth2User{

	private User user;//콤포지션
	
	//-> 오브젝트화 시키지않고 Map을 이용해서 통째로 보관함
	private Map<String,Object> attributes;
	
	//일반 로그인
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	
	//OAuth 로그인
	public PrincipalDetails(User user,Map<String,Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}
	
	
	
	//이게 없으면 session에서 유저 를 못씀
	public User getUser() {
		return user;
	}


	//해당 유저의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		//return null; 사용하지 않을 예정
		return (String) attributes.get("sub");
	}

}
