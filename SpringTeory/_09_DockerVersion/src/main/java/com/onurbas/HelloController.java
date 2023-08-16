package com.onurbas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/v1","/surum1"})
public class HelloController {

  @GetMapping("/hello")
  public String sayHello(){
	return "hello docker";
  }
}
