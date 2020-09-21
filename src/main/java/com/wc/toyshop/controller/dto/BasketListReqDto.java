package com.wc.toyshop.controller.dto;

import java.util.List;

import lombok.Data;

//장바구니 목록을 서버에 보낼때 사용하는 Dto였으나 id를 배열로 받을때 사용함
@Data
public class BasketListReqDto {
	private List<String> idList;
}
