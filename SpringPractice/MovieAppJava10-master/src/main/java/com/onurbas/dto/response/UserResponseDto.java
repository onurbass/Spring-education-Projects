package com.onurbas.dto.response;

import com.onurbas.repository.enums.EUserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
 // email id name phone surName userType
		  private Long id;
		  private String email;
		  private String name;
		  private String surName;
		  private String phone;
		  private EUserType userType=EUserType.USER;
}
