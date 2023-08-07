package com.onurbas.controller;

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

  @GetMapping(USER)
  public ResponseEntity<List<User>> findAll() {
	return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping(USER + "/{userId}")
  public ResponseEntity<User> findById(@PathVariable(name = "userId") Long id) {
	return ResponseEntity.ok(userService.findById(id));
  }

  @PostMapping(USER)
  public ResponseEntity<User> save(@RequestBody User user) {
	return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
  }
  @PutMapping(USER /*+ "/{userId}"*/)
  public ResponseEntity<User> update(@RequestBody User user) {
	return ResponseEntity.ok(userService.save(user));
  }

  @DeleteMapping(USER + "/{userId}")
  public ResponseEntity<Void> deleteById(@PathVariable(name = "userId") Long id) {
	userService.deleteById(id);
	return ResponseEntity.noContent().build();
  }



}
