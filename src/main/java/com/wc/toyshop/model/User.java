package com.wc.toyshop.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
