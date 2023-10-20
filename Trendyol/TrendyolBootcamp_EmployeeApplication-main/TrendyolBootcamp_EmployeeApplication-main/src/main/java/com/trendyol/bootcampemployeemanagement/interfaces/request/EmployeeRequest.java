package com.trendyol.bootcampemployeemanagement.interfaces.request;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String phone;

    @NotBlank
    private String department;

    @Email
    private String email;

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotBlank
    private String region;

    @NotBlank
    private String district;

    @NotBlank
    private String street;

    @NotBlank
    private String zipCode;

    private Boolean status;

    @Min(18)
    @Max(65)
    private Integer age;
}
