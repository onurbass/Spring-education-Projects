package com.mimaraslan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// http://localhost:9090/fallback/auth
@RestController
@RequestMapping("/fallback")
public class FallbackController {

  @GetMapping("/auth")
  public ResponseEntity<String> fallbackAuth() {
	return ResponseEntity.ok("Hi, this is a fallback method.auth");
  }
  @GetMapping("/sale")
  public ResponseEntity<String> fallbackSale() {
	return ResponseEntity.ok("Hi, this is a fallback method.sale");
  }

}
