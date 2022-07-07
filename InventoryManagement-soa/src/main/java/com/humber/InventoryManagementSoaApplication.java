package com.humber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class InventoryManagementSoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementSoaApplication.class, args);
	}

}
