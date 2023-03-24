package com.damian.msvcitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableAutoConfiguration(exclude  ={DataSourceAutoConfiguration.class})
public class MsvcItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcItemApplication.class, args);
	}

}
