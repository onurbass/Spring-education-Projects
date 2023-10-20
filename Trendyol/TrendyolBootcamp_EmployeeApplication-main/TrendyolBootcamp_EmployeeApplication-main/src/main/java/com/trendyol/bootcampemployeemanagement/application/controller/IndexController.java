package com.trendyol.bootcampemployeemanagement.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/swagger")
    public String index() {
        return "redirect:/swagger-ui/index.html";
    }
}
