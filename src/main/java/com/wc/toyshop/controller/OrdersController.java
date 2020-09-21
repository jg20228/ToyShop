package com.wc.toyshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wc.toyshop.config.auth.LoginUserAnnotation;
import com.wc.toyshop.config.auth.dto.LoginUser;
import com.wc.toyshop.controller.dto.BasketListReqDto;
import com.wc.toyshop.controller.dto.OrdersReqDto;
import com.wc.toyshop.controller.respdto.CommonRespDto;
import com.wc.toyshop.service.OrdersService;

@Controller
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;
	
	@GetMapping("/orders")
	public String orders(@LoginUserAnnotation LoginUser loginUser, Model model) {
		model.addAttribute("dtos", ordersService.결제내역조회(loginUser.getId()));
		return "orders/ordersList";
	}
	
	@GetMapping("/orders/detail/{ordersId}")
	public String ordersDetail(@LoginUserAnnotation LoginUser loginUser, @PathVariable int ordersId, Model model) {
		model.addAttribute("dto", ordersService.결제상세내역(loginUser, ordersId));
		return "orders/ordersDetail";
	}
	
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
