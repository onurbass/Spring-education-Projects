package com.onurbas.web_view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

  //http://localhost:8080/hello?firstName=OnurBaş
  @GetMapping("/hello")
  public String hello(Model model,
					  @RequestParam(value = "firstName",defaultValue = "Bilgi yok") String firstName
					  ){
model.addAttribute("firstName",firstName);
	return "hello";
  }
}
