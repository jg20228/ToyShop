package com.wc.toyshop.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class User {
	private int id;
	private String username;
	private String email;
	private String password;
	private String name;
	private String address;
	private String phone;
	private String gender;
	private String profileImage;
	private String role;
	private String provider;
	private String providerId;
	private Timestamp createDate;
	
	@Builder
	public User(int id,String username, String email, String password, String name, String address, String phone,
			String gender, String profileImage, String role, String provider, String providerId, Timestamp createDate) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.gender = gender;
		this.profileImage = profileImage;
		this.role = role;
		this.provider = provider;
		this.providerId = providerId;
		this.createDate = createDate;
	}
}
