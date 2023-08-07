package com.onurbas.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  @Lob
  private String content;
  @Column(name = "published_at")
  @Builder.Default
  private LocalDateTime date = LocalDateTime.now();

  @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
  private User user;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @ToString.Exclude
  private Category category;

}