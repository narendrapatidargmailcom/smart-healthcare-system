package com.coder.gateway.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomExceptionHandler  implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper= new ObjectMapper();

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        Map<String,Object> errorDetails = new HashMap<>();
        errorDetails.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetails.put("error","Internal server error");
        errorDetails.put("message",ex.getMessage());
        errorDetails.put("path", exchange.getRequest().getPath().value());

        String errorResponse;

        try {
            errorResponse = objectMapper.writeValueAsString(errorDetails);
        } catch (Exception e) {
           errorResponse = "Failed to serilize the errordetails Object";
        }

        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();

        return exchange.getResponse().writeWith(Mono.just(dataBufferFactory.wrap(errorResponse.getBytes(StandardCharsets.UTF_8))));
    }
}
