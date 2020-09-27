package com.wc.toyshop.model;

import lombok.Getter;
import lombok.Setter;


//OAuth2 client 라이브러리를 사용하지 않고 카카오 로그인할때 사용할때 씀
@Getter
@Setter
public class OAuthToken {
	private String access_token;
	private String token_type;
	private String refresh_token;
	private String expires_in;
	private String scope;
	private String refresh_token_expires_in;
}