package com.wc.toyshop.controller.respdto;

import lombok.Data;

@Data
public class BasketRespDto {
	private int id;
	private String imgUrl;
	private String name;
	private int price;
	private int count;
}