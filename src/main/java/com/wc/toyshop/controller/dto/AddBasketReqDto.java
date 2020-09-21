package com.wc.toyshop.controller.dto;

import lombok.Data;

//장바구니 추가할때 서버에 요청할때 쓰이는 Dto
@Data
public class AddBasketReqDto {
	private int userId;
	private int productId;
	private int count;
}
