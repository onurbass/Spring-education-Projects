package com.socialmedia.controller;

import com.socialmedia.dto.request.ActivationRequestDto;
import com.socialmedia.dto.request.LoginRequestDto;
import com.socialmedia.dto.request.RegisterRequestDto;
import com.socialmedia.dto.request.UpdateRequestDto;
import com.socialmedia.dto.response.RegisterResponseDto;
import com.socialmedia.service.AuthService;
import com.socialmedia.utility.JWTTokenManager;
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

  private final JWTTokenManager jwtTokenManager;

  @PostMapping(REGISTER)
  public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
	return ResponseEntity.ok(authService.register(dto));
  }

  @PostMapping(LOGIN)
  public ResponseEntity<String> login(@RequestBody LoginRequestDto dto){
	return ResponseEntity.ok(authService.login(dto));
  }

  @PostMapping(ACTIVATE_STATUS)
  public ResponseEntity<String> login(@RequestBody ActivationRequestDto dto){
	return ResponseEntity.ok(authService.activateStatus(dto));
  }

  @GetMapping("/create_token")
  public  ResponseEntity<String> createToken(Long id){
	return ResponseEntity.ok(jwtTokenManager.createToken(id).get());
  }

  @GetMapping("/get_id_from_token")
  public  ResponseEntity<Long> getIdFromToken(String token){
	return ResponseEntity.ok(jwtTokenManager.getIdFromToken(token).get());
  }
  @PutMapping(UPDATE)
  public ResponseEntity<String> updateAuth (@RequestBody UpdateRequestDto updateRequestDto){

	return ResponseEntity.ok(authService.updateAuth(updateRequestDto));
  }

}
