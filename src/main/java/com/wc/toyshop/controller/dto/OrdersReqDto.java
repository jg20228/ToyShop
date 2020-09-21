package com.wc.toyshop.controller.dto;

import java.util.List;

import lombok.Data;

//아임포트로 주문이 완료되었을때 받아온 값들을 서버에 요청할때 사용
@Data
public class OrdersReqDto {
	private String impId;
	private String merchantId;
	private String applyNum;
	private int totalPay;
	private List<String> idList;
}
