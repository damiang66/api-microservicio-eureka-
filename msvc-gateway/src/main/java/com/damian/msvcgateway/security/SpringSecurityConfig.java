package com.damian.msvcgateway.security;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
public class SpringSecurityConfig {
    @Bean
    public SecurityWebFilterChain confioure(ServerHttpSecurity http){
        return http.authorizeExchange().anyExchange().authenticated()
                .and().csrf().disable().build();
    }
}
