package com.onurbas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo2")
public class DemoController2 {
  /*
   http metotları
   Get
   Post
   Put
   Delete
   */
  @GetMapping()
  public String hello() {
	return "Demo 2";
  }

  @GetMapping("/getname")
  public String getName() {
	return "Sena";
  }

}
