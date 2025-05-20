package com.coder.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

    @GetMapping("/auth")
    public String authFallback() {
        return "Authentication Service is temporarily unavailable. Please try again later.";
    }
}
