package com.onurbas.controller;

import com.onurbas.model.User;
import com.onurbas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.onurbas.constant.RestApiUrl.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping(USER)
  public List<User> findAll() {
	return userService.findAll();
  }

  @GetMapping(USER + "/{userId}")
  public User findById(@PathVariable(name = "userId") Long id) {
	return userService.findById(id);
  }

  @PostMapping(USER)
  public User save(@RequestBody User user) {
	return userService.save(user);
  }

  @DeleteMapping(USER + "/{userId}")
  public void deleteById(@PathVariable(name = "userId") Long id) {
	userService.deleteById(id);
  }

}
