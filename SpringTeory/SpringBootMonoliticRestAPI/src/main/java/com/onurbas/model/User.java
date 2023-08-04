package com.onurbas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
  private String firstName;
  private String lastName;
  @Column(unique = true)//,nullable = false, )
  private String email;
 // @Column(nullable = false)
  private String password;

  @ToString.Exclude
  @OneToMany(mappedBy = "author",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  List<Post> userPosts;



}
