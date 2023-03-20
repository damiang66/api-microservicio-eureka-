package com.damian.msvcitem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


import javax.management.MXBean;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
