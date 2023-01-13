package com.example.apibasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApibasicApplication {

	public static void main(String[] args) {
		//
		int a =10;
		int b =10;

		System.out.println(a+b);
		SpringApplication.run(ApibasicApplication.class, args);
	}

}
