package com.bilgeadam.dto.response;

import com.bilgeadam.repository.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Sehir{
    private Long id;
    private String isim;
    private String bolge;
    private String blur;
}
