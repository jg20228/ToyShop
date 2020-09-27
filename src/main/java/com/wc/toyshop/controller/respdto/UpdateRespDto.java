package com.wc.toyshop.controller.respdto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UpdateRespDto {
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
	private Timestamp createDate;
}
