package com.trendyol.bootcampemployeemanagement.interfaces.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {
    private String country;
    private String city;
    private String region;
    private String district;
    private String street;
    private String zipCode;
}
