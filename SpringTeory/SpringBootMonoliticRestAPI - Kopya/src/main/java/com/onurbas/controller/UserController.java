package com.onurbas.controller;

import com.onurbas.dto.response.UserDTO;
import com.onurbas.model.User;
import com.onurbas.model.User;
import com.onurbas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.onurbas.constant.RestApiUrl.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping(USER)
  public ResponseEntity<UserDTO> save(@RequestBody User user) {
	return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
  }

  @GetMapping(USER)
  public ResponseEntity<List<UserDTO>> findAll() {
	return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping(USER + "/{userId}")
  public ResponseEntity<UserDTO> findById(@PathVariable(name = "userId") Long id) {
	return ResponseEntity.ok(userService.findById(id));
  }

  @PutMapping(USER + "/{userId}")
  public ResponseEntity<UserDTO> update(@RequestBody User user,@PathVariable(name = "userId") Long id) {
	user.setId(id);
	return ResponseEntity.ok(userService.save(user));
  }

  @DeleteMapping(USER + "/{userId}")
  public ResponseEntity<Void> deleteById(@PathVariable(name = "userId") Long id) {
	userService.deleteById(id);
	return ResponseEntity.noContent().build();
  }

}
