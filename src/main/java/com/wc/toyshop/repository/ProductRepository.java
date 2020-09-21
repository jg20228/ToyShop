package com.wc.toyshop.repository;

import java.util.List;

import com.wc.toyshop.controller.respdto.ProductRespDto;
import com.wc.toyshop.model.Product;

public interface ProductRepository {
	public List<Product> findAll();
	public List<ProductRespDto> findAllJoin();
	
	//상품등록
	public void productSave(Product product);
	//수정갈때 정보들고감
	public Product findById(int id);
	//상품수정
	public void update(Product product);
	//상품삭제
	public void deleteById(int id);
}
