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
  private String name;
  private String country;
  @Lob
  private String summary;
  private Double rating;
  private String url;
  private LocalDate premired;
  @ElementCollection
  private Set<Long> comments;

}
