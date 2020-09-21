package com.wc.toyshop.repository;

import java.util.List;

import com.wc.toyshop.controller.dto.BasketSumDto;
import com.wc.toyshop.controller.respdto.OrdersDetailDto;
import com.wc.toyshop.controller.respdto.OrdersRespDto;
import com.wc.toyshop.model.Orders;
import com.wc.toyshop.model.Orders_detail;

public interface OrdersRepository {
	//관리자가 특정 유저의 결제내역 볼때
	public List<Orders> findAllByUserIdJoin(int userId);
	//관리자가 모든 유저의 결제내역을 볼 때
	public List<Orders> findAllJoin();
	
	//
	public Orders findByOrdersId(int ordersId);
	
	//해당 유저의 모든 주문내역 보기
	public List<Orders> findByUserId(int userId);
	
	//해당 유저의 결제내역 상세보기내용 담기
	public List<OrdersDetailDto> findByUserIdOrdersIdJoin(int userId, int ordersId);
	
	//장바구니에서 선택된 상품들 가격 더하기
	public BasketSumDto sum(int userId, int basketId);
	
	//Orders 저장
	public void saveOrders(Orders orders);
	//Orders_detail 저장
	public void saveDetails(Orders_detail detail);
}
