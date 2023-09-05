package com.socialmedia.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterElasticModel {
private String id;
  private Long authId;
  private String username;
  private String email;
}
