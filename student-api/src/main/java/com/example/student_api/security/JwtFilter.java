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
    public void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException,IOException
    {
      String header = request.getHeader("Authorization");
      if(header!=null && header.startsWith("Bearer "))
      {
        String token = header.substring(7);
        String username = jwtUtils.extractUsername(token);
        if(username !=null)
        {
          UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(username,null,new ArrayList<>());
          SecurityContextHolder.getContext().setAuthentication(authtoken);
        }
      }
      filterChain.doFilter(request, response);
    }
                     
}
