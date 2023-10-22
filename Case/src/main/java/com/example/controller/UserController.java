package com.example.controller;

import com.example.constant.Endpoints;
import com.example.dto.UserRequestDTO;
import com.example.dto.UserResponseDTO;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1" + Endpoints.USER)
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/")
  public ResponseEntity<List<UserResponseDTO>> getUsers() {

	return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> getUserById(@PathVariable ObjectId id) {

	return ResponseEntity.ok(userService.findById(id));
  }

  @PostMapping("/")
  public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO userRequestDTO) {

	return ResponseEntity.created(URI.create("/")).body(userService.saveUser(userRequestDTO));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable ObjectId id) {
	userService.deleteUser(id);
	return ResponseEntity.noContent().build();
  }

}
