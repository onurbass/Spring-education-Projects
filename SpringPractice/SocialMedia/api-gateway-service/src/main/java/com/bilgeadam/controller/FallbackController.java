package com.bilgeadam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

  @GetMapping("/auth_service")
  public ResponseEntity<String> authServiceFallback() {
	return ResponseEntity.ok("Auth Service is not available. Please try again later");
  }

  @GetMapping("/user_service")
  public ResponseEntity<String> userServiceFallbak() {
	return ResponseEntity.ok("User Service is not available. Please try again later");
  }
}
