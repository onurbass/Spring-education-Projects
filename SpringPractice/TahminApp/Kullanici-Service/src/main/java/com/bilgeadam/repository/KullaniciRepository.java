package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KullaniciRepository extends JpaRepository<Kullanici,Long> {

    Optional<Kullanici> findByUsernameAndPassword(String username,String password);

}
