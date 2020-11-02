package com.example.demo.config.error;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;



@Component
public class GlobalErrorWebExceptionHandler extends
    AbstractErrorWebExceptionHandler {

	
	
    public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ApplicationContext applicationContext, ServerCodecConfigurer configurer) {
        super(errorAttributes, resourceProperties, applicationContext);
        this.setMessageWriters(configurer.getWriters());
    }

    @SuppressWarnings("deprecation")
	@Override
    protected RouterFunction<ServerResponse> getRoutingFunction(
      ErrorAttributes errorAttributes) {
        return RouterFunctions
            .route(RequestPredicates.all(), request -> {
                Throwable error = errorAttributes.getError(request);
//                logger.error("Se ha producido un error por lo que ha llegado al manejador de excepciones");
                return ServerResponse.status(400).syncBody(error.getMessage()).doOnEach(serverResponseSignal -> {
                });
            });
    }

}
 
