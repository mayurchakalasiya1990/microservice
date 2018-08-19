package com.adjectiveservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AdjectiveServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdjectiveServiceApplication.class, args);
	}
}
