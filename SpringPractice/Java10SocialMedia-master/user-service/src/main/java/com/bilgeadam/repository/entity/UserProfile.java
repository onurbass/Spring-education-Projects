package com.bilgeadam.repository.entity;


import com.bilgeadam.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

@SuperBuilder
@Document
public class UserProfile extends BaseEntity{
    @Id
    private String id;
    private Long authId;
    private String username;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    private String about;
    private String name;
    private String surName;
    private LocalDate birthDate;
    @Builder.Default
    private EStatus status = EStatus.PENDING;
}
