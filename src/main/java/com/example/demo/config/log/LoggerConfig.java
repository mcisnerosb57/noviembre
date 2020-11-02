//package com.example.demo.config.log;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.ByteArraySerializer;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import com.github.danielwegener.logback.kafka.KafkaAppender;
//import com.github.danielwegener.logback.kafka.delivery.AsynchronousDeliveryStrategy;
//import com.github.danielwegener.logback.kafka.keying.NoKeyKeyingStrategy;
//
//import ch.qos.logback.classic.Logger;
//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
//import ch.qos.logback.classic.spi.ILoggingEvent;
//import ch.qos.logback.core.util.StatusPrinter;
//
//@Configuration(proxyBeanMethods = false)
//public class LoggerConfig {
//	
//	private static String KAFKA_ADDRESS =  "poc.kafka.address";
//
//
//	@Bean
//	@Primary
//	LoggerContext createLoggerContext() {
//		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
//		 KafkaAppender<ILoggingEvent> kafkaAppender = new KafkaAppender();
//		    kafkaAppender.setDeliveryStrategy(new AsynchronousDeliveryStrategy());
//		    kafkaAppender.setKeyingStrategy(new NoKeyKeyingStrategy());
//		    kafkaAppender.setName("Main");
//		    kafkaAppender.setTopic("log");
//		    kafkaAppender.setContext(loggerContext);
//		    kafkaAppender.addProducerConfigValue(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
//		    kafkaAppender.addProducerConfigValue(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
//		    kafkaAppender.addProducerConfigValue("bootstrap.servers", "127.0.0.1:9092");
//		    kafkaAppender.addProducerConfigValue("acks", "0");
//		    kafkaAppender.addProducerConfigValue("clientId", "prueba");
//		    kafkaAppender.addProducerConfigValue("max.block.ms", "0");
//		    kafkaAppender.addProducerConfigValue("retries", "5");
//	    PatternLayoutEncoder encoder = new PatternLayoutEncoder();
//	    encoder.setContext(loggerContext);
//	    encoder.setPattern("%r %thread %level - %msg%n");
//	    encoder.start();
//	    kafkaAppender.setEncoder(encoder);
//	    kafkaAppender.start();
//	   
//	    kafkaAppender.isStarted();
//	    Logger logbackLogger = loggerContext.getLogger("ROOT");
//	    logbackLogger.addAppender(kafkaAppender);
//	    StatusPrinter.print(loggerContext);
//	    // log something
//	    logbackLogger.debug("Prueba Kafka");
//	    
//		return loggerContext;
//
//	}
//}
