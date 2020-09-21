package com.wc.toyshop.controller.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//관리자가 상품수정으로 서버에 요청할때 쓰임
@Data
public class UpdateProductReqDto {
	private int id;
	private MultipartFile file;
	private String name;
	private String disc;
	private int price;
}
