package com.coder.auth.security.jwt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenrator {
    public static void main(String[] args) {
        String plainPassword = "Narendra@123";

        // Create an instance of BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Encode the password
        String hashedPassword = passwordEncoder.encode(plainPassword);

        System.out.println("Plain Password: " + plainPassword);
        System.out.println("Hashed Password: " + hashedPassword);
    }
}
