package com.trendyol.bootcampemployeemanagement.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Address {
    private String country;
    private String city;
    private String region;
    private String district;
    private String street;
    private String zipCode;
}
