package com.sherpaerp.library.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SherpaerpLibraryEurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SherpaerpLibraryEurekaserverApplication.class, args);
	}

}
