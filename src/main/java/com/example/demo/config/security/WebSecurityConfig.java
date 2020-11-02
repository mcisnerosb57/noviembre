package com.example.demo.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration(proxyBeanMethods = false)
@EnableWebFluxSecurity
public class WebSecurityConfig {

	@Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {	
		 return http.authorizeExchange()
			        .pathMatchers("/**")
                    .permitAll()
                .and()
                .httpBasic()
                    .disable()
                .csrf()
                    .disable()
                .formLogin()
                    .disable()
                .logout()
                    .disable()
				 .addFilterBefore(new JwtWebFilter(), SecurityWebFiltersOrder.FIRST).build();
	        
  }

}
