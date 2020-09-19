package com.wc.toyshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wc.toyshop.config.auth.LoginUserAnnotation;
import com.wc.toyshop.config.auth.dto.LoginUser;
import com.wc.toyshop.controller.dto.BasketListReqDto;
import com.wc.toyshop.controller.dto.CommonRespDto;
import com.wc.toyshop.controller.dto.OrdersReqDto;
import com.wc.toyshop.service.OrdersService;

@Controller
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;
	
	@PostMapping("/orders/sum")
	public @ResponseBody CommonRespDto<?> totalPay(@LoginUserAnnotation LoginUser loginUser, @RequestBody BasketListReqDto dto) {
		int sum = ordersService.결제전합계(loginUser.getId(), dto);
		return new CommonRespDto<Integer>(1,sum);
	}
	
	
	@PostMapping("/orders")
	public @ResponseBody CommonRespDto<?> ordersSave(@LoginUserAnnotation LoginUser loginUser, @RequestBody OrdersReqDto dto) {
		ordersService.결제후테이블(loginUser.getId(), dto);
		return new CommonRespDto<Integer>(1,1);
	}
	
}
