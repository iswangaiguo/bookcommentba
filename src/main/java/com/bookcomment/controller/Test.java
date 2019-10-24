package com.bookcomment.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

	@GetMapping("/user")
	public Principal getUser(Principal principal) {
		return principal;
	}
	
	@GetMapping("/api")
	public String getApi() {
		return "api";
	}
	
	@GetMapping("/ss")
	public String getss() {
		return "ss";
	}
	
}
