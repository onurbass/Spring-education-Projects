package com.onurbas.repository.entity;


import com.onurbas.repository.enums.EUserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String surName;
    @Column(length = 32)
    private String password;
    @Column(length = 50)
    private String email;
    @Column(length = 15)
    private String phone;
    @ElementCollection
    private Set<Long> favMovies;
    @ElementCollection
    private Set<Long> favGenres;
    @ElementCollection
    private Set<Long> comments;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EUserType userType=EUserType.USER;

}
