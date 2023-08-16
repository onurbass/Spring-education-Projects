package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Sehir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SehirRepository extends JpaRepository<Sehir,Long> {


    Long countAllBy();
    @Query(value = "select s.id from sehir as s order by s.id desc limit 1",nativeQuery = true)
    Long getLAstId();

    Sehir getFirstByOrderByIdDesc();

}
