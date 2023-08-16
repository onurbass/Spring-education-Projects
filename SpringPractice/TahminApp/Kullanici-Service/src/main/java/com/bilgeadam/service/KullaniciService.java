package com.bilgeadam.service;

import com.bilgeadam.dto.request.GirisRequesDto;
import com.bilgeadam.dto.request.KayitRequestDto;
import com.bilgeadam.dto.response.KayitResponseDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.KullaniciManagerException;
import com.bilgeadam.mapper.IKullaniciMapper;
import com.bilgeadam.repository.KullaniciRepository;
import com.bilgeadam.repository.entity.Kullanici;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class KullaniciService extends ServiceManager<Kullanici,Long> {

    private final KullaniciRepository kullaniciRepository;

    public KullaniciService(KullaniciRepository kullaniciRepository) {
        super(kullaniciRepository);
        this.kullaniciRepository = kullaniciRepository;
    }

    public KayitResponseDto kayit(KayitRequestDto dto) {
        Kullanici kullanici= IKullaniciMapper.INSTANCE.toKullanici(dto);
        save(kullanici);
        return IKullaniciMapper.INSTANCE.toKayitResponseDto(kullanici);
    }


    public Boolean giris(GirisRequesDto dto){
        Optional<Kullanici> kullanici=kullaniciRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (kullanici.isEmpty()){
            throw new KullaniciManagerException(ErrorType.USER_NOT_FOUND);
        }
        return  true;
    }

    public Boolean updateAktifTahminId(Long id,Long tahminId) {
        Optional<Kullanici> kullanici=kullaniciRepository.findById(id);
        if (kullanici.isEmpty()){
            throw new KullaniciManagerException(ErrorType.USER_NOT_FOUND);
        }
        kullanici.get().setAktifTahminId(tahminId);
        kullanici.get().getTahminler().add(tahminId);
        update(kullanici.get());

        return true;
    }
}
