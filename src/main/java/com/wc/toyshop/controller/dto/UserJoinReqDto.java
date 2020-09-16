package com.wc.toyshop.controller.dto;

import lombok.Data;

@Data
public class UserJoinReqDto {
	private String username;
	private String email;
	private String password;
}
