package com.wc.toyshop.controller.respdto;

import java.util.List;

import com.wc.toyshop.model.Orders;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdersDetailRespDto {
	private Orders orders;
	private List<OrdersDetailDto> details;
}
