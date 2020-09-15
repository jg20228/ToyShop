package com.wc.toyshop.model;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Basket {
	private int id;
	private int userId;
	private int productId;
	private int count;
	private Timestamp createDate;
}
