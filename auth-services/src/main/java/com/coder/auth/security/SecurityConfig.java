package com.coder.auth.security;

import com.coder.auth.security.jwt.AuthEntryPoint;
import com.coder.auth.security.jwt.AuthTokenFilter;
import com.coder.auth.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceimpl;

    @Autowired
    private AuthEntryPoint anuthorizehandler;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return  authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(); // Returns a new instance of AuthTokenFilter
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsServiceimpl);
        return  authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

         httpSecurity.csrf(AbstractHttpConfigurer :: disable)
                 .exceptionHandling(exception ->
                         exception.authenticationEntryPoint(anuthorizehandler))
                 .sessionManagement(session ->
                         session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .authorizeHttpRequests(auth -> auth
                // Configure authorization for HTTP requests
                .requestMatchers("/api/auth/**").permitAll()
                // Allow public access to auth endpoints
                .requestMatchers("/api/test/**").permitAll()
                // Allow public access to test endpoints
                .anyRequest().permitAll());

         httpSecurity.authenticationProvider(authenticationProvider());

         return  httpSecurity.build();

    }


}
