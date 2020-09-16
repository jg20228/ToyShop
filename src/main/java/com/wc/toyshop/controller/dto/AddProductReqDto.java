package com.wc.toyshop.controller.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AddProductReqDto {
	private MultipartFile file;
	private String name;
	private String disc;
	private int price;
}
