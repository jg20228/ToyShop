package com.wc.toyshop.controller.respdto;

import java.util.List;

import com.wc.toyshop.config.auth.dto.LoginUser;
import com.wc.toyshop.model.Orders;

import lombok.Builder;
import lombok.Data;

//결제내역을 보여줄때 날짜 + 그 결제에대한 상세보기들
@Data
@Builder
public class OrdersRespDto {
	private LoginUser user;
	private Orders orders;
	private List<OrdersDetailDto> details;
}
