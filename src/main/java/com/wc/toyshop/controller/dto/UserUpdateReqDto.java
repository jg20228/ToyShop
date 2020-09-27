package com.wc.toyshop.controller.dto;

import lombok.Data;

@Data
public class UserUpdateReqDto {
	private int id;
	private String username;
	private String email;
	private String name;
	private String password;
	private String phone;
	private String address;
	private String gender;
}
