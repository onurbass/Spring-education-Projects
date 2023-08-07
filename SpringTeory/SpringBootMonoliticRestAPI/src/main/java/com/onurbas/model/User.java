package com.onurbas.model;

import com.onurbas.model.enums.EUserType;
import jakarta.persistence.*;
import lombok.*;

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
  @Column(unique = true,nullable = false)
  private String email;
  @Column(nullable = false)
  private String password;
  private EUserType userType;
  @ToString.Exclude
  @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  List<Post> userPosts;



}
