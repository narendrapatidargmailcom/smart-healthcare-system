package com.coder.gateway.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class LoggingFilter implements GlobalFilter, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        LOGGER.info("Request Method: {}, Request URI: {}",
                 exchange.getRequest().getMethod(),
                exchange.getRequest().getURI()
                );
        return chain.filter(exchange).doFinally((singleType)->
             LOGGER.info("Response Status code: {}",exchange.getResponse().getStatusCode()));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;  // Ensures this filter runs last
    }
}
