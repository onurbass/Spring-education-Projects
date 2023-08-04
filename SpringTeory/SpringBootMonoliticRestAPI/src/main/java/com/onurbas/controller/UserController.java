package com.onurbas.controller;

import com.onurbas.model.User;
import com.onurbas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/hello")
  public String sayHello() {
	return "Hello";
  }
  @GetMapping("/users")
  public List<User> findAll(){
	return userService.findAll();
  }
  @GetMapping("/users/{id}")
  public Optional<User> findById(@PathVariable(name = "id") Long id){
	return userService.findById(id);
  }

  @PostMapping("/users")
  public User save(User user){
	return userService.save(user);
  }

}
