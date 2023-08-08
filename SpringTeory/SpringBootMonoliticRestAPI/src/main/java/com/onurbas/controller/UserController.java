package com.onurbas.controller;

import com.onurbas.dto.response.UserResponseDTO;
import com.onurbas.dto.request.UserRequestDTO;
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
  public ResponseEntity<UserResponseDTO> save(@RequestBody UserRequestDTO userRequestDTO) {
	return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userRequestDTO));
  }

  @GetMapping(USER)
  public ResponseEntity<List<UserResponseDTO>> findAll() {
	return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping(USER + "/{userId}")
  public ResponseEntity<UserResponseDTO> findById(@PathVariable(name = "userId") Long id) {
	return ResponseEntity.ok(userService.findById(id));
  }

  @PutMapping(USER + "/{userId}")
  public ResponseEntity<UserResponseDTO> update(@RequestBody UserRequestDTO userRequestDTO,@PathVariable(name = "userId") Long id) {


	return ResponseEntity.ok(userService.update(userRequestDTO,id));
  }

  @DeleteMapping(USER + "/{userId}")
  public ResponseEntity<Void> deleteById(@PathVariable(name = "userId") Long id) {
	userService.deleteById(id);
	return ResponseEntity.noContent().build();
  }

}
