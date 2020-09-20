package com.wc.toyshop.repository;

import java.util.List;

import com.wc.toyshop.controller.dto.BasketSumDto;
import com.wc.toyshop.controller.respdto.OrdersDetailRespDto;
import com.wc.toyshop.controller.respdto.OrdersRespDto;
import com.wc.toyshop.model.Orders;
import com.wc.toyshop.model.Orders_detail;

public interface OrdersRepository {
	public List<Orders> findByUserId(int userId);
	public List<OrdersDetailRespDto> findByUserIdOrdersIdJoin(int userId, int ordersId);
	public List<Orders_detail> findByUserIdDetails(int userId);
	public BasketSumDto sum(int userId, int basketId);
	public void saveOrders(Orders orders);
	public void saveDetails(Orders_detail detail);
}
