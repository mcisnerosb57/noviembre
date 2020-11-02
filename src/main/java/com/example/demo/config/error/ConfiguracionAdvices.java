package com.example.demo.config.error;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;

@Configuration(proxyBeanMethods = false)
@Component
public class ConfiguracionAdvices {

	@Bean
	GlobalErrorWebExceptionHandler globalexcetpion(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
			ApplicationContext applicationContext, ServerCodecConfigurer codecConfigurer) {
				return new GlobalErrorWebExceptionHandler(errorAttributes, resourceProperties, applicationContext, codecConfigurer);
		
	}
	
}