package com.example.demo;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;

//import com.github.danielwegener.logback.kafka.KafkaAppender;
//import com.github.danielwegener.logback.kafka.delivery.AsynchronousDeliveryStrategy;
//import com.github.danielwegener.logback.kafka.keying.NoKeyKeyingStrategy;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.util.StatusPrinter;
import com.example.demo.model.dto.GenerarPrecioIvaIn;
import com.example.demo.model.func.AplicarIva;


@ComponentScan
@SpringBootApplication(exclude = SpringDataWebAutoConfiguration.class, proxyBeanMethods = false)
public class DemoApplication implements ApplicationContextInitializer<GenericApplicationContext> {

	public static void main(String[] args) {
		
	    SpringApplication.run(DemoApplication.class, args);
	}

	@Bean()
	public Function<GenerarPrecioIvaIn, String> generar() {
		return precio -> new AplicarIva().apply(precio);
	}

	@Override
	public void initialize(GenericApplicationContext applicationContext) {

	}

}
