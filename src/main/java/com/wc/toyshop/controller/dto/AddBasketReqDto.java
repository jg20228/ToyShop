package com.wc.toyshop.controller.dto;

import lombok.Data;

@Data
public class AddBasketReqDto {
	private int userId;
	private int productId;
	private int count;
}
