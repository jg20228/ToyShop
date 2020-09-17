package com.wc.toyshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wc.toyshop.controller.respdto.ProductRespDto;
import com.wc.toyshop.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public List<ProductRespDto> 모든상품() {
		return productRepository.findAllJoin();
	}
}
