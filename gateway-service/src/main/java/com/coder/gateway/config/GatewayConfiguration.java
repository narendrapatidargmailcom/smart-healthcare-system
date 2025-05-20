package com.coder.gateway.config;

import com.coder.gateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@Configuration
public class GatewayConfiguration {

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder) {

        System.out.println("inside routes");
       return  routeLocatorBuilder.routes()

                // Route configuration for auth-service
                .route("auth-services",r-> r.path("/api/auth/**")
                        .filters(f-> f
                                .circuitBreaker(c-> c.setName("authCircuitBreaker").setFallbackUri("forward:/fallback/auth"))
                         .filter( new RemoveDuplicateHeadersFilter())
                         .filter((exchange,chain)-> {

                             if(exchange.getRequest().getMethod() == HttpMethod.OPTIONS){
                                 exchange.getResponse().setStatusCode(HttpStatus.OK);
                                 return exchange.getResponse().setComplete();
                             }
                           return chain.filter(exchange);
                         })

                ).uri("http://localhost:8081")
                )
                .build();


    }
}
