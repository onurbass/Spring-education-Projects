package com.bilgeadam.controller;

import com.bilgeadam.dto.request.TahminRequestDto;
import com.bilgeadam.dto.response.TahminResponeDto;
import com.bilgeadam.service.TahminService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tahmin")
public class TahminController {


    private final TahminService tahminService;

    @GetMapping("/olustur")
    public ResponseEntity<TahminResponeDto> tahminOlustur(@RequestParam Long userId){

        return ResponseEntity.ok(tahminService.olustur(userId));
    }

    @PostMapping("/tahminyap")
    public ResponseEntity<String> tahminYap(@RequestBody TahminRequestDto dto){

        return ResponseEntity.ok(tahminService.tahminYap(dto));
    }
}
