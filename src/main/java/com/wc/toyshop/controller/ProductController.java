package com.wc.toyshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wc.toyshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	//상품 목록
	@GetMapping("/product")
	public String productList(Model model) {
		model.addAttribute("products", productService.모든상품());
		return "/product/productList";
	}
}
