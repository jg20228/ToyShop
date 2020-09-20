package com.wc.toyshop.controller.respdto;

import lombok.Data;

@Data
public class OrdersDetailRespDto {
	private int ordersId;
	private String imgUrl;
	private String name;
	private int price;
	private int count;
	private String status;
}
