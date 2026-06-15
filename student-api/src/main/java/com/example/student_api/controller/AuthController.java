package com.example.student_api.controller;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student_api.entity.User;
import com.example.student_api.security.JwtUtils;



@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    JwtUtils jwtutils;
    @Autowired
    PasswordEncoder passwordEncoder;
    ArrayList<User> list = new ArrayList<>();
    @PostMapping("/register")
    public String register(@RequestBody User  user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       list.add(user);
       return "User Registrated ";
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user)
    {
       boolean found = list.stream()
          .filter(u->u.getUsername().equals(user.getUsername())
           &&  passwordEncoder.matches(user.getPassword(),u.getPassword()))
           .findFirst()
           .isPresent();
         
           if(found)
           {
             return ResponseEntity.ok(jwtutils.generateToken(user.getUsername()));
           }
           else{
            return ResponseEntity.status(401).body("Invalid Credentials");
           }
          
      
    }
}
