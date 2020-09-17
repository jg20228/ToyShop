package com.wc.toyshop.controller.respdto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductRespDto {
	private int id;
	private String name;
	private String imgUrl;
	private String disc;
	private int price;
	private Timestamp createDate;
	private int stockId;
	private int productId;
	private int count;
}
