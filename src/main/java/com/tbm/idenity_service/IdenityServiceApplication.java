package com.tbm.idenity_service.dto;

import com.tbm.idenity_service.service.UserService;import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdenityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserService.IdenityServiceApplication.class, args);
	}

}
