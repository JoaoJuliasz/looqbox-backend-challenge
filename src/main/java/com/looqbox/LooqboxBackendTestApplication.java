package com.looqbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LooqboxBackendTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LooqboxBackendTestApplication.class, args);
	}

}
