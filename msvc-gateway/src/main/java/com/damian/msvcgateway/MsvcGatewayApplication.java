package com.damian.msvcgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsvcGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcGatewayApplication.class, args);
	}

}
