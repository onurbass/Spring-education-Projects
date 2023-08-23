package com.socialmedia.controller;

import com.socialmedia.dto.request.ActivationRequestDto;
import com.socialmedia.dto.request.LoginRequestDto;
import com.socialmedia.dto.request.RegisterRequestDto;
import com.socialmedia.dto.response.RegisterResponseDto;
import com.socialmedia.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.socialmedia.constant.EndPoints.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping(REGISTER)
  public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto) {
	return ResponseEntity.ok(authService.register(dto));

  }

  @PostMapping(LOGIN)
  public ResponseEntity<String> login(@RequestBody LoginRequestDto dto) {
	return ResponseEntity.ok(authService.login(dto));

  }

  @PostMapping(ACTIVATE_STATUS)
  public ResponseEntity<String> activateStatus(@RequestBody ActivationRequestDto dto ){

	return  ResponseEntity.ok(authService.activateStatus(dto));
  }
}
