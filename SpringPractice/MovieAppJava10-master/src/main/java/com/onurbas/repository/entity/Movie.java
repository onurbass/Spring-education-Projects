package com.onurbas.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private Set<Long> genres;
    private String language;
    private String image;
    private String name;
    private String country;
    private Double rating;
    @Lob
    private String summary;
    private LocalDate premired;
    private String url;
    @ElementCollection
    private Set<Long>  comments;
}
