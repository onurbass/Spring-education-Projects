package com.socialmedia.controller;

import com.socialmedia.dto.request.LoginRequestDto;
import com.socialmedia.dto.request.RegisterRequestDto;
import com.socialmedia.dto.response.RegisterResponseDto;
import com.socialmedia.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto dto) {
        return ResponseEntity.ok(authService.register(dto));


    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(authService.login(dto));


    }

}
