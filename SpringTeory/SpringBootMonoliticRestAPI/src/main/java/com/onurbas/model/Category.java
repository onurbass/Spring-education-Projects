package com.onurbas.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String categoryName;
  private String description;

  @ToString.Exclude
  @ManyToMany(cascade = CascadeType.ALL,mappedBy = "postCategories",fetch = FetchType.LAZY)
  private List<Post> posts;

}
