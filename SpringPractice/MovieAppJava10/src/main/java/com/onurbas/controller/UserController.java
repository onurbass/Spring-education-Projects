package com.onurbas.controller;

import com.onurbas.repository.entity.User;
import com.onurbas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;



  @GetMapping("/save")
  public ResponseEntity<User> createUser(String name,String surname,String email,String password,String phone) {
	User user = User.builder().name(name).surName(surname).email(email).password(password).phone(phone).build();

	try {
	  return ResponseEntity.ok(userService.createUser(user));
	} catch (Exception e) {
	  return ResponseEntity.badRequest().build();
	}
  }

}
