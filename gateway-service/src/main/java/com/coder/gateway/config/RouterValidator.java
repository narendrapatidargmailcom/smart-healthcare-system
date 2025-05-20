package com.coder.gateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public static final Set<String> openApiEndpoints = Set.of(
            "api/auth/signup",
            "api/auth/login"// Add more public endpoints as needed
    );
    public Predicate<ServerHttpRequest> isSecured = req -> openApiEndpoints
            .stream().noneMatch(uri -> req.getURI().getPath().contains(uri));
}
