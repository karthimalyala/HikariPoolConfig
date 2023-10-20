package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaxxer.hikari.HikariDataSource;

@RestController
public class HelloWorldController {
	@Autowired
	private HikariDataSource datasource;

	@GetMapping("/")
	public String welcome() {
		int maxPoolSize =  datasource.getMaximumPoolSize();
		return "Maximum Pool Size = " +maxPoolSize;
	}

}