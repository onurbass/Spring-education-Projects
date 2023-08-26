package com.socialmedia.controller;

import com.socialmedia.dto.UserSaveRequestDto;
import com.socialmedia.repository.entity.UserProfile;
import com.socialmedia.service.UserService;
import com.socialmedia.utility.JWTTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.socialmedia.constant.EndPoints.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final JWTTokenManager jwtTokenManager;

@PostMapping(SAVE)
  public ResponseEntity<Boolean> save(@RequestBody UserSaveRequestDto dto){
  return ResponseEntity.ok(userService.createNewUser(dto));
  }
  @PostMapping(ACTIVATE_STATUS)
  public ResponseEntity<String> activateStatus(@RequestParam String token){
    return ResponseEntity.ok(userService.activateStatus(token));
  }

}
