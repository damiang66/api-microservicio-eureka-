package com.damian.msvcproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.damian.comun.msvccomun.entity"})
public class MsvcProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcProductosApplication.class, args);
	}

}
