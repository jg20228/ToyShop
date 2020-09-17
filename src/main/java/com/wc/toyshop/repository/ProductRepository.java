package com.wc.toyshop.repository;

import java.util.List;

import com.wc.toyshop.controller.respdto.ProductRespDto;
import com.wc.toyshop.model.Product;

public interface ProductRepository {
	public List<Product> findAll();
	public List<ProductRespDto> findAllJoin();
	public void productSave(Product product);
	public Product findById(int id);
	public void update(Product product);
	public void deleteById(int id);
}
