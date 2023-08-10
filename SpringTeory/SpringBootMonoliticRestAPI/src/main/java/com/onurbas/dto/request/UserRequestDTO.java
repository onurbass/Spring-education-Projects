package com.onurbas.dto.request;

import com.onurbas.model.enums.EUserType;
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
  private String email;
  private String password;

}
