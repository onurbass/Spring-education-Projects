package com.bilgeadam.manager;

import com.bilgeadam.dto.response.Sehir;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:9091/sehir",decode404 = true,name = "tahmin-sehir")
public interface ISehirManager {

    @GetMapping("/{id}")
    public ResponseEntity<Sehir> sehirGetir(@PathVariable Long id);

    @GetMapping("/randomsehir")
    public  ResponseEntity<Sehir> sehirGetir();
}
