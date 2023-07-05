package com.ctoutweb.example.authV1.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctoutweb.example.authV1.security.UserPrincipal;

@RestController
@RequestMapping("/api/v1")
public class HelloController {
	
	@GetMapping("/")
	public String greetin() {
		return "Hello World";
	}
	
	@GetMapping("/secured")
	public String secured(@AuthenticationPrincipal UserPrincipal user) {
		return "If you see, then you get a valid token" + user.getEmail() + user.getId();
	}

}
