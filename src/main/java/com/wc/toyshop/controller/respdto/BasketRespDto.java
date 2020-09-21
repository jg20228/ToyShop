package com.wc.toyshop.controller.respdto;

import lombok.Data;

//장바구니 볼때 응답하는 Dto
@Data
public class BasketRespDto {
	private int id;
	private String imgUrl;
	private String name;
	private int price;
	private int count;
}