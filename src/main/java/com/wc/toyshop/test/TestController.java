package com.wc.toyshop.test;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	//특정 메소드에 걸때 사용
	@Secured("ROLE_ADMIN")
	@GetMapping("/admin/index")
	public @ResponseBody String adminTest() {
		System.out.println("adminTest");
		return "어드민테스트";
	}
}
