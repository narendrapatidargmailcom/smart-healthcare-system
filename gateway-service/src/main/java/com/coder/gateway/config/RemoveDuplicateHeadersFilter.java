package com.coder.gateway.config;

import com.fasterxml.classmate.Filter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


public class RemoveDuplicateHeadersFilter implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getResponse().getHeaders().remove(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN);
        return chain.filter(exchange);
    }
}
