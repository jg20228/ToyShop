package com.wc.toyshop.model;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Product {
	private int id;
	private String name;
	private String imgUrl;
	private String disc;
	private int price;
	private Timestamp createDate;
}
