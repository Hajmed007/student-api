package com.example.student_api.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

    public String generateToken(String name)
    {
       return Jwts.builder()
                  .setSubject(name)
                  .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                  .signWith(SignatureAlgorithm.HS384, "secretkey")
                  .compact();
    }
    public String extractUsername(String token)
    {
        return Jwts.parser()
                   .setSigningKey("secretkey")
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }
    public boolean validate(String token,String name)
    {
        return extractUsername(token).equals(name);
    }
    
} 