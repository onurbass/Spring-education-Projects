package com.bilgeadam.controller;

import com.bilgeadam.repository.entity.Skor;
import com.bilgeadam.service.SkorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/skor")
public class SkorController {


    private final SkorService skorService;
    @GetMapping("/sirala")
    public ResponseEntity<List<Skor>> sirala(){

        return ResponseEntity.ok(skorService.sirala());
    }
}
