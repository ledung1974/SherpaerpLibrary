package com.sherpaerp.library.borrowersmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SherpaerpLibraryBorrowersmicroserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SherpaerpLibraryBorrowersmicroserviceApplication.class, args);
	}
}
