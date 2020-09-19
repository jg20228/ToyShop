package com.wc.toyshop.repository;

import com.wc.toyshop.controller.dto.BasketSumDto;
import com.wc.toyshop.model.Orders;
import com.wc.toyshop.model.Orders_detail;

public interface OrdersRepository {
	public BasketSumDto sum(int userId, int basketId);
	public void saveOrders(Orders orders);
	public void saveDetails(Orders_detail detail);
}
