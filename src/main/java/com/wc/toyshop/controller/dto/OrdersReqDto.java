package com.wc.toyshop.controller.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrdersReqDto {
	private String impId;
	private String merchantId;
	private String applyNum;
	private int totalPay;
	private List<String> idList;
}
