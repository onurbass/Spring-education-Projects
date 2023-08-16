package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Skor;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ISkorRepository extends JpaRepository<Skor,Long> {
    Optional<Skor> findByUserId(Long userId);

    List<Skor> findAllByOrderBySkorDesc();
}
