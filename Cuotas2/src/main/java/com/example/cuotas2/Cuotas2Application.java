package com.example.cuotas2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Cuotas2Application {

    public static void main(String[] args) {
        SpringApplication.run(Cuotas2Application.class, args);
    }

}
