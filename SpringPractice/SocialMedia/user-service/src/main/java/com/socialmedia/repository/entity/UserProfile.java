package com.socialmedia.repository.entity;

import com.socialmedia.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity

public class UserProfile extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long authId;
  private String username;
  private String email;
  private String phone;
  private String address;
  private String avatar;//url
  private String about;
  private String name;
  private String surName;
  private LocalDate birthDate;

  @Enumerated(EnumType.STRING)
  @Builder.Default
  private EStatus status = EStatus.PENDING;

}
