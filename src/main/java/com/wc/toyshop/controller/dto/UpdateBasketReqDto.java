package com.wc.toyshop.controller.dto;

import lombok.Data;

//장바구니 수량을 바꿔서 서버에 요청할떄 쓰임
@Data
public class UpdateBasketReqDto {
	private int id;
	private int count;
}
