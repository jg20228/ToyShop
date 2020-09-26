package com.wc.toyshop.config.oauth.provider;

import java.util.Map;

public class KaKaoUserInfo implements OAuth2UserInfo{

	private String id;
	private Map<String,Object> attributes;//getAttributes();
	
	public KaKaoUserInfo(String id, Map<String,Object> attributes) {
		this.id = id;
		this.attributes = attributes;
	}
	
	@Override
	public String getProviderId() {
		return id;
	}

	@Override
	public String getProvider() {
		return "kakao";
	}

	@Override
	public String getEmail() {
		return (String) attributes.get("email");
	}

	@Override
	public String getName() {
		return (String) attributes.get("nickname");
	}
	
	public String profile_image() {
		return (String) attributes.get("profile_image");
	}
}
