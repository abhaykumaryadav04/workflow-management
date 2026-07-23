package com.a4b.automation.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.a4b.automation.auth.dto.AuthResponse;
import com.a4b.automation.auth.dto.LoginRequest;
import com.a4b.automation.auth.dto.RegisterRequest;
import com.a4b.automation.auth.service.AuthService;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
@Autowired
private AuthService authService;

      @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register( @RequestBody RegisterRequest request) {
        return authService.register(request);
    }
      @PostMapping("/login")
      @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthResponse login( @RequestBody LoginRequest request) {
        return authService.login(request);
    }
  }

