package com.onurbas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloController {

    @GetMapping
    public String hello() {
        return "Satis Service";
    }

    @GetMapping("/info")
    public String info() {
        return "INFO: Satis Service";
    }
}
