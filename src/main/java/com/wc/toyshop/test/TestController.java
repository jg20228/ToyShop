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
	
	@GetMapping("/test/join")
	public String testJoin() {
		System.out.println("testJoin");
		return "auth/join";
	}
	
	@GetMapping("/test/login")
	public String testLogin() {
		System.out.println("testLogin");
		return "auth/login";
	}
}
