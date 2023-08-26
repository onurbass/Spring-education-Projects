package com.socialmedia.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDto {
  @NotBlank(message = "username cannot be blank")
  private String username;
  @NotBlank(message = "email cannot be blank")
  @Email
  private String email;
  @NotBlank(message = "password cannot be blank")
  @Size(min = 5, max = 32, message = "password must be between 5 and 32 characters")
  // @Pattern(regexp = "^.*(?=.{5,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$") //kriterler
  private String password;
}
