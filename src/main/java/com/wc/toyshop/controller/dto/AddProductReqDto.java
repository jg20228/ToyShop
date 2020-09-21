package com.wc.toyshop.controller.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


//상품 추가누르고 서버에 요청할때 받는 Dto
@Data
public class AddProductReqDto {
	private MultipartFile file;
	private String name;
	private String disc;
	private int price;
}
