package com.wc.toyshop.repository;

import java.util.List;

import com.wc.toyshop.controller.respdto.BasketRespDto;
import com.wc.toyshop.model.Basket;

public interface BasketRepository {
	public Basket findById(int id);
	public Basket findyByUserIdAndProductId(int userId, int productId);
	public List<Basket> findAll();
	public List<BasketRespDto> findyAllJoin(int userId);
	public void save(Basket basket);
	public void update(Basket basket);
	public void deleteById(int id);
}
