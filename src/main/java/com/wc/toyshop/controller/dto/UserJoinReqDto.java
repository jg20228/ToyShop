package com.wc.toyshop.controller.dto;

import lombok.Data;

//간단한 회원가입(임시, 수정필요)
@Data
public class UserJoinReqDto {
	private String username;
	private String email;
	private String password;
}
