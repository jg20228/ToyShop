package com.wc.toyshop.controller.respdto;

import java.sql.Timestamp;
import java.util.List;

import com.wc.toyshop.model.Orders;
import com.wc.toyshop.model.Orders_detail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdersRespDto {
	private Orders orders;
	private List<OrdersDetailRespDto> details;
}
