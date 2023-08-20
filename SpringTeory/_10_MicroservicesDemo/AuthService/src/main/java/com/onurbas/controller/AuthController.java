package com.onurbas.controller;

import com.onurbas.constant.EndPoints;
import com.onurbas.dto.request.DoLoginRequestDTO;
import com.onurbas.dto.request.RegisterRequestDTO;
import com.onurbas.dto.response.RegisterResponseDTO;
import com.onurbas.repository.entity.Auth;
import com.onurbas.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(EndPoints.AUTH)
public class AuthController {

  private final AuthService authService;

  public ResponseEntity<String> doLogin(@RequestBody RegisterRequestDTO dto) {
	return ResponseEntity.ok(authService.doLogin(dto));
  }

  @PostMapping(EndPoints.REGISTER)
  public ResponseEntity<Auth> register(@RequestBody RegisterRequestDTO dto) {
      /*
        Auth auth = new Auth();
        auth.setUsername(dto.getUsername());
        auth.setEmail(dto.getEmail());
        auth.setPassword(dto.getPassword());
        authService.save(auth);
        */

	Auth auth = authService.save(
			Auth.builder()
				.username(dto.getUsername())
				.email(dto.getEmail())
				.password(dto.getPassword())
				.build()
								);

	return ResponseEntity.ok(auth);
  }
  public ResponseEntity<String> doLogin(@RequestBody DoLoginRequestDTO dto){
	return null;
  }
}
