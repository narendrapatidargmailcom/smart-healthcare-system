package com.coder.notification;

import com.coder.notification.service.EmailService;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    EmailService service;

    @GetMapping("/hello")
    public String testmain(){
        try {

            service.sendSimplemain("narendravire@gmail.com","Hello from Spring Boot","testing ,msg from alpha");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "sucessfully send msg";
    }
}
