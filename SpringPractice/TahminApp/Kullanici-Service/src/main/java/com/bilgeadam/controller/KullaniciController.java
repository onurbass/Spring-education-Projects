package com.bilgeadam.controller;

import com.bilgeadam.dto.request.GirisRequesDto;
import com.bilgeadam.dto.request.KayitRequestDto;
import com.bilgeadam.dto.response.KayitResponseDto;
import com.bilgeadam.repository.entity.Kullanici;
import com.bilgeadam.service.KullaniciService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/kullanici")
public class KullaniciController {

    private final KullaniciService kullaniciService;

    @PostMapping("/kayit")
    public ResponseEntity<KayitResponseDto> kayit(@RequestBody KayitRequestDto dto){

        return ResponseEntity.ok(kullaniciService.kayit(dto));
    }
    @PostMapping("/giris")
    public ResponseEntity<Boolean> giris(@RequestBody GirisRequesDto dto){
        return ResponseEntity.ok(kullaniciService.giris(dto));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Kullanici>> findAll(){
        return ResponseEntity.ok(kullaniciService.findAll());
    }

    @GetMapping("/updatetahmin/{id}/{tahminId}")
    public ResponseEntity<Boolean> updateAktifTahminId(@PathVariable Long id,@PathVariable long tahminId){

        return ResponseEntity.ok(kullaniciService.updateAktifTahminId(id,tahminId));
    }
}
