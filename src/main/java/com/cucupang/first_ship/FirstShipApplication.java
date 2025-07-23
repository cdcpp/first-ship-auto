package com.cucupang.first_ship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FirstShipApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstShipApplication.class, args);
	}

}
