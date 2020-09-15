package com.wc.toyshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Stock {
	private int id;
	private int productId;
	private int count;
}
