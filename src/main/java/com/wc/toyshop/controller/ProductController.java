package com.wc.toyshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wc.toyshop.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/product")
	public String productList(Model model) {
		model.addAttribute("products", productService.모든상품());
		return "/product/productList";
	}
}
