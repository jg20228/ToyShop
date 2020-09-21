package com.wc.toyshop.controller.respdto;

import lombok.Data;

//Orders_Detail 테이블 행 하나
@Data
public class OrdersDetailDto {
	private int ordersId;
	private String imgUrl;
	private String name;
	private int price;
	private int count;
	private String status;
}
