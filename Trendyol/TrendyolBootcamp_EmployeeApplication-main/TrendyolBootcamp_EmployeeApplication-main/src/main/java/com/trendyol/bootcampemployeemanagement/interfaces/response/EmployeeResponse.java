package com.trendyol.bootcampemployeemanagement.interfaces.response;

import com.trendyol.bootcampemployeemanagement.domain.Address;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private UUID id;
    private String name;
    private String surname;
    private String phone;
    private String department;
    private String email;
    private Boolean status;
    private Integer age;
    private Address address;
}
