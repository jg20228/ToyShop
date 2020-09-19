package com.wc.toyshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wc.toyshop.controller.dto.BasketListReqDto;
import com.wc.toyshop.controller.dto.BasketSumDto;
import com.wc.toyshop.controller.dto.OrdersReqDto;
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
	public int 결제전합계(int userId, BasketListReqDto dto) {
		
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
