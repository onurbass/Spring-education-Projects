package com.onurbas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/test")
public class RestController {

  @GetMapping("/hello")
  public String sayHello(){

	return "Hello World";
  }
  @GetMapping("/sa")
  public String sa(){

	return "Hello sa";
  }
}
