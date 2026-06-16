package com.example.student_api.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtils jwtUtils;
    
    @Override
    public void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
        
        String header = request.getHeader("Authorization");
        System.out.println("DEBUG: Header = " + header);
        
        if(header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            System.out.println("DEBUG: Token extracted = " + token.substring(0, Math.min(20, token.length())) + "...");
            
            try {
                String username = jwtUtils.extractUsername(token);
                System.out.println("DEBUG: Username extracted = " + username);
                
                if(username != null) {
                    UsernamePasswordAuthenticationToken authToken = 
                        new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println("DEBUG: Authentication set for " + username);
                }
            } catch (Exception e) {
                System.out.println("DEBUG: Token validation failed = " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("DEBUG: No valid Authorization header");
        }
        
        filterChain.doFilter(request, response);
    }
}