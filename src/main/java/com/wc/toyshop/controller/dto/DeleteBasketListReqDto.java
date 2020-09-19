package com.wc.toyshop.controller.dto;

import java.util.List;

import lombok.Data;

@Data
public class DeleteBasketListReqDto {
	private List<String> idList;
}
