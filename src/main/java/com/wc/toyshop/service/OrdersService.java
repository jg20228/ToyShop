package com.wc.toyshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wc.toyshop.config.auth.dto.LoginUser;
import com.wc.toyshop.controller.dto.BasketListReqDto;
import com.wc.toyshop.controller.dto.BasketSumDto;
import com.wc.toyshop.controller.dto.OrdersReqDto;
import com.wc.toyshop.controller.respdto.OrdersDetailDto;
import com.wc.toyshop.controller.respdto.OrdersRespDto;
import com.wc.toyshop.model.Basket;
import com.wc.toyshop.model.Orders;
import com.wc.toyshop.model.Orders_detail;
import com.wc.toyshop.repository.BasketRepository;
import com.wc.toyshop.repository.OrdersRepository;

@Service
public class OrdersService {
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private BasketRepository basketRepository;
	
	@Transactional
	public OrdersRespDto 결제상세내역(LoginUser loginUser, int ordersId) {
		Orders orders = ordersRepository.findByOrdersId(ordersId);
		
		//DB의 구매 userId와 로그인된 userId 비교
		if(orders.getUserId()!=loginUser.getId()) {
			//여기서 처리
		}
		
		List<OrdersDetailDto> details = ordersRepository.findByUserIdOrdersIdJoin(loginUser.getId(), ordersId);
		OrdersRespDto ordersRespDto = OrdersRespDto.builder()
				.user(loginUser)
				.details(details)
				.orders(orders)
				.build();
		return ordersRespDto;
	}
	
	@Transactional
	public List<OrdersRespDto> 결제내역조회(int userId){
		//return할 dto 생성
		List<OrdersRespDto> respDtos = new ArrayList<OrdersRespDto>();
		
		//해당 유저의 모든 주문을 가져옴
		List<Orders> orders = ordersRepository.findByUserId(userId);
		
		for (Orders order : orders) {
			
			List<OrdersDetailDto> details = ordersRepository.findByUserIdOrdersIdJoin(userId, order.getId());
			OrdersRespDto ordersRespDto = OrdersRespDto.builder()
					.orders(order)
					.details(details)
					.build();
			respDtos.add(ordersRespDto);
		}
		return respDtos;
	}
	
	@Transactional
	public int 결제전합계(int userId, BasketListReqDto dto) {
		//수정필요 : stock 고려 
		int sum = 0;
		BasketSumDto sumDto = null;
		for (String basketId : dto.getIdList()) {
			sumDto = ordersRepository.sum(userId, Integer.parseInt(basketId));
			sum = sum+ sumDto.getCount() * sumDto.getPrice();
		}
		System.out.println(sum);
		return sum;
	}
	
	@Transactional
	public void 결제후테이블(int userId, OrdersReqDto dto) {
		System.out.println("orders 시작");
		Orders orders = Orders.builder()
				.userId(userId)
				.impId(dto.getImpId())
				.merchantId(dto.getMerchantId())
				.applyNum(dto.getApplyNum())
				.totalPay(dto.getTotalPay())
				.build();
		ordersRepository.saveOrders(orders);
		System.out.println("orders 끝");
		System.out.println("detail 시작");
		for (String basketId : dto.getIdList()) {
			Basket basket = basketRepository.findById(Integer.parseInt(basketId));
			Orders_detail detail = Orders_detail.builder()
					.ordersId(orders.getId())
					.productId(basket.getProductId())
					.count(basket.getCount())
					.status("준비중")
					.build();
			ordersRepository.saveDetails(detail);
			basketRepository.deleteById(Integer.parseInt(basketId));
		}
		System.out.println("detail 끝");
	}
}
