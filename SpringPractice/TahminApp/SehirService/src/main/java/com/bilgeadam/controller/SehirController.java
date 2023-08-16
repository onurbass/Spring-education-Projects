package com.bilgeadam.controller;

import com.bilgeadam.dto.request.SehirRequestDto;
import com.bilgeadam.repository.entity.Sehir;
import com.bilgeadam.service.SehirService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sehir")
public class SehirController {

    private final SehirService sehirService;

    @PostMapping("/ekle")
    public ResponseEntity<Sehir> ekle(@RequestBody SehirRequestDto dto){

     return     ResponseEntity.ok(sehirService.ekle(dto));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Sehir> sehirGetir(@PathVariable Long id){

        return ResponseEntity.ok(sehirService.sehirGetir(id));
    }
    @GetMapping("/randomsehir")
    public  ResponseEntity<Sehir> sehirGetir(){

        return ResponseEntity.ok(sehirService.randomSehirGetir());
    }
}
