package com.sherpaerp.library.loanofborrower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SherpaerpLibraryLoanofborrowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SherpaerpLibraryLoanofborrowerApplication.class, args);
	}
}
