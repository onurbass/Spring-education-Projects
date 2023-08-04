package com.onurbas.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
  private LocalDateTime date=LocalDateTime.now();
  /*@Column(name = "user_id")*/
  @ManyToOne(cascade ={CascadeType.PERSIST,CascadeType.MERGE} )
  private User author;

  @ToString.Exclude
  @ManyToMany(cascade = CascadeType.ALL)
  private List<Category> postCategories;

}