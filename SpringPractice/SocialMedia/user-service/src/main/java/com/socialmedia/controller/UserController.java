package com.socialmedia.controller;

import com.socialmedia.dto.request.UserProfileUpdateRequestDto;
import com.socialmedia.dto.request.UserSaveRequestDto;
import com.socialmedia.dto.response.UserProfileFindAllResponseDto;
import com.socialmedia.repository.entity.UserProfile;
import com.socialmedia.repository.enums.EStatus;
import com.socialmedia.service.UserService;
import com.socialmedia.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.socialmedia.constant.EndPoint.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final JwtTokenManager jwtTokenManager;

  @PostMapping(SAVE) //https:localhost/7070/user
  public ResponseEntity<Boolean> save(@RequestBody UserSaveRequestDto dto) {
	return ResponseEntity.ok(userService.createNewUser(dto));
  }

  @PostMapping(ACTIVATE_STATUS)
  public ResponseEntity<String> activateStatus(@RequestParam String token) {
	return ResponseEntity.ok(userService.activateStatus(token));
  }

  @PutMapping(UPDATE)
  public ResponseEntity<String> updateUserProfile(@RequestBody @Valid UserProfileUpdateRequestDto dto) {
	return ResponseEntity.ok(userService.updateUserProfile(dto));
  }

  @GetMapping(FIND_ALL)
  public ResponseEntity<List<UserProfileFindAllResponseDto>> findAll() {
	return ResponseEntity.ok(userService.findAllUserProfile());
  }

  @GetMapping("/find_by_username/{username}")
  public ResponseEntity<UserProfile> findByUsername(@PathVariable String username) {
	return ResponseEntity.ok(userService.findByUsername(username));
  }

  @GetMapping("/find_by_status/{eStatus}")
  public ResponseEntity<List<UserProfile>> findByUsername(@PathVariable EStatus eStatus) {
	return ResponseEntity.ok(userService.findByStatus(eStatus));
  }

  @DeleteMapping(DELETE_BYID)
  public ResponseEntity<String> deleteById(@RequestParam Long id) {
	userService.deleteUser(id);
	return ResponseEntity.ok("User deleted..");
  }
}
