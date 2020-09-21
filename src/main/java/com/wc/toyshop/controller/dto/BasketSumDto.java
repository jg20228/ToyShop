package com.wc.toyshop.controller.dto;

import lombok.Data;

//현재 장바구니의 합계 계산시 이용됨
@Data
public class BasketSumDto {
	private int count;
	private int price;
}
