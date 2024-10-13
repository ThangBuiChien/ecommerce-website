package com.devchangetheworld.ewebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(EWebsiteApplication.class, args);
	}

}
