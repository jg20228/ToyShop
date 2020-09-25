package com.wc.toyshop.config.oauth;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.wc.toyshop.config.auth.PrincipalDetails;
import com.wc.toyshop.config.auth.dto.LoginUser;
import com.wc.toyshop.model.User;
import com.wc.toyshop.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private HttpSession session;
	@Autowired
	private UserRepository userRepository;
	
	@Value("${wc.key}")
	private String wcKey;
	
	//구글로 부터 받은 userRequset 데이터에 대한 후처리되는 함수
	//함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		//RegistrationId로 어떤 OAuth로 로그인 했는지 알 수 있음
		System.out.println("userRequest :"+userRequest.getClientRegistration());
		System.out.println("getAccessToken :"+userRequest.getAccessToken());
		
		OAuth2User oauth2User = super.loadUser(userRequest);
		//구글 로그인 버튼 클릭 -> 구글 로그인창-> 로그인을 완료하면 -> code를 리턴받음(OAuth-Client라이브러리)->AccessToken요청
		//userRequest 정보 -> loadUser함수 호출 -> 구글로부터 회원프로필 받아줌
		System.out.println("getAttributes :"+oauth2User.getAttributes());
		//회원가입을 강제로 진행해볼 예정
		
		String provider = userRequest.getClientRegistration().getClientId(); // google
		String providerId = oauth2User.getAttribute("sub");
		String username = provider+"_"+providerId; //google_10000000
		String password = bCryptPasswordEncoder.encode(wcKey);
		String email = oauth2User.getAttribute("email");
		String role = "ROLE_USER";
		
		User userEntity = userRepository.findByProviderAndProviderId(provider, providerId);
		if(userEntity==null) {
			System.out.println("userEntity==null");
			userEntity = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.role(role)
					.provider(provider)
					.providerId(providerId)
					.name("이름")
					.address("입력필요")
					.phone("입력필요")
					.gender("성별")
					.build();
			userRepository.save(userEntity);
		}
		//세션 여기가 맞나 모르겠음
		session.setAttribute("loginUser", new LoginUser(userEntity));
		return new PrincipalDetails(userEntity, oauth2User.getAttributes());
	}
} 

