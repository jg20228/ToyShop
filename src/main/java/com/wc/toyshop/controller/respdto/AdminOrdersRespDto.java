package com.wc.toyshop.controller.respdto;

import java.sql.Timestamp;

import lombok.Data;

//관리자가 결제내역볼때 응답해주는 Dto
@Data
public class AdminOrdersRespDto {
	private int id; 
	private int userId;
	private String username; 
	private String impId;
	private String merchantId; 
	private String applyNum;
	private int totalPay; 
	private Timestamp createDate;
}
