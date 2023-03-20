package com.damian.msvcitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@RibbonClient(name="msvc-productos")
@SpringBootApplication
public class MsvcItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcItemApplication.class, args);
	}

}
