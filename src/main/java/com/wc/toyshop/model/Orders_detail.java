package com.wc.toyshop.model;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Orders_detail {
	private int id;
	private int ordersId;
	private int productId;
	private int count;
	private String status;
	private Timestamp createDate;
}
