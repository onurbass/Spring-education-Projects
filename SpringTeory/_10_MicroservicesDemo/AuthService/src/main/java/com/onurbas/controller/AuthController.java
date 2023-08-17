package com.onurbas.controller;

import com.onurbas.constant.EndPoints;
import com.onurbas.repository.entity.Auth;
import com.onurbas.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping(EndPoints.AUTH)
public class AuthController {

  private final AuthService authService;

 /* public ResponseEntity<RegisterResponseDTO> register(RegisterRequestDTO dto) {
	return ResponseEntity.ok(authService.save(auth));
  }*/
}
