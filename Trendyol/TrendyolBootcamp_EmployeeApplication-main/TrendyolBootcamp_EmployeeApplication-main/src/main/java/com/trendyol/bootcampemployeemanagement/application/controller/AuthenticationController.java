package com.trendyol.bootcampemployeemanagement.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {

    @GetMapping("basic/basic-authentication")
    public ResponseEntity<Void> basicAuthentication() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("bearer/bearer-authentication")
    public ResponseEntity<Void> bearerAuthentication() {
        return ResponseEntity.noContent().build();
    }
}
