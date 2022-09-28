package com.mb.api.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.mb.api.constants.GenericConstants.TEST_URL;

@RestController
@RequestMapping(TEST_URL)
public class TestController extends BaseController
{
	@GetMapping("/home")
	public String home() {
		return "<h1>Welcome Back !! To the home page</h1>";
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "<h1>This is Welcome Page !!!</h1>";
	}
	
	@GetMapping("/student")
	public String student() {
		return "<h1>This is Student Page !!!</h1>";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "<h1>This is Admin Page</h1>";
	}
	
	@GetMapping("/employee")
	public String employee() {
		return "<h1>This is Employee Page</h1>";
	}
	

}
