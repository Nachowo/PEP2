package com.example.cuotas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CuotasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuotasApplication.class, args);
	}

}
