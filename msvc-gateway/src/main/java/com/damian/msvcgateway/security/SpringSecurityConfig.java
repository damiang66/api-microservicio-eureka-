package com.damian.msvcgateway.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.web.server.SecurityWebFilterChain;


@EnableWebFluxSecurity
public class SpringSecurityConfig {
    @Autowired
    private  JwtAuthenticationFilter authenticationFilter;
    @Bean
    public SecurityWebFilterChain confioure(ServerHttpSecurity http){
        return http.authorizeExchange()
                .pathMatchers("/api/oauth/oauth/**").permitAll()
                .pathMatchers(HttpMethod.GET,"/api/productos",
                        "/api/items",
                        "/api/usuarios/usuarios",
                        "/api/items/{id}/cantidad/{cantidad}",
                        "/api/{id}").permitAll()
                .pathMatchers(HttpMethod.GET,"/api/usuarios/usuarios/{id}")
                .hasAnyRole("ADMIN","USER")
                .pathMatchers("/api/productos/**","/api/items/**","/api/usuarios/usuarios/**").hasRole("ADMIN")
                .anyExchange().authenticated()
                .and()
                        .addFilterAt(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)

                .csrf().disable().build();
    }
}
