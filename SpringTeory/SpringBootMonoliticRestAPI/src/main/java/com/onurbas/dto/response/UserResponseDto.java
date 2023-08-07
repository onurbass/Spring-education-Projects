package com.onurbas.dto.response;

import com.onurbas.model.enums.EUserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String surName;
    private String email;
    private String phone;
    private EUserType userType;
}
