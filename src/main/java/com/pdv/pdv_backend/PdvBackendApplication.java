package com.pdv.pdv_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling
public class PdvBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdvBackendApplication.class, args);
	}

}
