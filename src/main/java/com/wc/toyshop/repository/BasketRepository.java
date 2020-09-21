package com.wc.toyshop.repository;

import java.util.List;

import com.wc.toyshop.controller.respdto.BasketRespDto;
import com.wc.toyshop.model.Basket;

public interface BasketRepository {
	//장바구니 id로 1건 검색
	public Basket findById(int id);
	
	//update할때 해당 유저가 해당 상품을 들고있는지 검사하기 위함
	public Basket findyByUserIdAndProductId(int userId, int productId);
	
	//유저의 장바구니페이지 이동해서 장바구니에 담긴 상품을 보여줌
	public List<BasketRespDto> findAllJoin(int userId);
	
	//장바구니 저장
	public void save(Basket basket);
	
	//장바구니 수정
	public void update(Basket basket);
	
	//장바구니 삭제
	public void deleteById(int id);
}
