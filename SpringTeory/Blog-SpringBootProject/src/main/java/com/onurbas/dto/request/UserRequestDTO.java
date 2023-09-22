package com.onurbas.dto.request;

import com.onurbas.model.enums.EUserType;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
  private Long id;
  private String firstName;
  private String lastName;
  @Email(message = "@ siz email olmaz")
  private String email;
  private String password;


}
