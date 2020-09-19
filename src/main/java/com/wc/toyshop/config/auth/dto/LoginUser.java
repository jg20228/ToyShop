package com.wc.toyshop.config.auth.dto;

import com.wc.toyshop.model.User;

import lombok.Data;

@Data
public class LoginUser {
	private int id;
	private String username;
	private String email;
	private String name;
	private String address;
	private String phone;
	private String gender;
	private String profileImage;
	private String role;
	private String provider;
	private String providerId;
	
	public LoginUser(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.name = user.getName();
		this.address = user.getAddress();
		this.phone = user.getPhone();
		this.gender = user.getGender();
		this.profileImage = user.getProfileImage();
		this.role = user.getRole();
		this.provider = user.getProvider();
		this.providerId = user.getProviderId();
	}
}
