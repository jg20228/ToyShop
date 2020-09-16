package com.wc.toyshop.model;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
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
}
