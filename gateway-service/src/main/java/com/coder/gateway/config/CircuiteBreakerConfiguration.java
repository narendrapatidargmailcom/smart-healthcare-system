package com.coder.gateway.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;


@Configuration
public class CircuiteBreakerConfiguration {

   @Bean
  public Customizer<Resilience4JCircuitBreakerFactory> globleCustomConfiguration(){

       TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
               .timeoutDuration(Duration.ofSeconds(4))
               .build();


       CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
               .failureRateThreshold(50)
               .waitDurationInOpenState(Duration.ofMillis(1000))
               .slidingWindowSize(2)
               .build();


       return  factory -> factory.configureDefault(id-> new Resilience4JConfigBuilder(id)
               .timeLimiterConfig(timeLimiterConfig)
               .circuitBreakerConfig(circuitBreakerConfig)
               .build());


   }

}
