package com.wc.toyshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wc.toyshop.controller.dto.AddProductReqDto;
import com.wc.toyshop.controller.dto.CommonRespDto;
import com.wc.toyshop.controller.dto.UpdateProductReqDto;
import com.wc.toyshop.service.AdminService;
import com.wc.toyshop.service.ProductService;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;
	@Autowired
	private ProductService productService;
	
	//주문관리
	
	
	//유저관리
	
	
	//상품리스트 보기
	@GetMapping("/admin/product")
	public String productList(Model model) {
		model.addAttribute("products", productService.모든상품());
		return "/admin/product/productList";
	}
	//상품등록하러가기
	@GetMapping("/admin/productForm")
	public String addProductForm() {
		return "admin/product/productForm";
	}
	
	//상품등록하기
	@PostMapping("/admin/product")
	public String addProduct(AddProductReqDto product) {
		System.out.println("product : "+product);
		adminService.상품등록(product);
		return "redirect:/admin/product";
	}
	
	//상품수정하러가기
	@GetMapping("/admin/productUpdateForm/{id}")
	public String updateProductForm(@PathVariable int id, Model model) {
		model.addAttribute("product", adminService.상품수정가기(id));
		return "admin/product/productEditForm";
	}
	
	//상품수정하기
	@PostMapping("/admin/productUpdate")
	public String updateProduct(UpdateProductReqDto product) {
		System.out.println("product : "+product);
		adminService.상품수정(product);
		return "redirect:/admin/product";
	}
	
	//상품삭제하기
	@DeleteMapping("/admin/product")
	public @ResponseBody CommonRespDto<?> deleteProduct(int id) {
		adminService.상품삭제(id);
		return new CommonRespDto<String>(1, "삭제완료");

	}
	
}
