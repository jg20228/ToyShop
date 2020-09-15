package com.wc.toyshop.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("index")
	public String index() {
		System.out.println("index");
		return "index";
	}
	
	@GetMapping("/test/index")
	public String testIndex() {
		System.out.println("testIndex");
		return "index";
	}
}
