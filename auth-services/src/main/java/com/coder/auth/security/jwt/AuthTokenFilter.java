package com.coder.auth.security.jwt;

import com.coder.auth.security.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class); // Logger for logging errors

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userServiceImple;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         String token =  parseJwt(request);

        try {
            if(token!=null && jwtUtils.validateJwtToken(token)){
               String userName =jwtUtils.getUserNameFromJwtToken(token);

              UserDetails userDetails=  userServiceImple.loadUserByUsername(userName);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                    new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        } catch (Exception e) {
            logger.error("user can not set");
        }

        filterChain.doFilter(request,response);

    }


    private String parseJwt(HttpServletRequest request) {
        // Get the Authorization header from the request
        String headerAuth = request.getHeader("Authorization");

        // Check if the header is valid and starts with "Bearer "
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            // Extract the JWT token from the header
            return headerAuth.substring(7);
        }

        return null; // Return null if no valid token is found
    }
}
