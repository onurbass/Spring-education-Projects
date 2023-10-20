package com.trendyol.bootcampemployeemanagement.domain;

import com.trendyol.bootcampemployeemanagement.interfaces.request.EmployeeRequest;
import lombok.*;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    private UUID id;
    private String name;
    private String surname;
    private String phone;
    private String department;
    private String email;
    private Boolean status;
    private Integer age;
    private Address address;

    public static Employee createFrom(final EmployeeRequest request) {
        return Employee.builder()
                .address(Address.builder()
                        .country(request.getCountry())
                        .city(request.getCity())
                        .region(request.getRegion())
                        .district(request.getDistrict())
                        .street(request.getStreet())
                        .zipCode(request.getZipCode())
                        .build())
                .name(request.getName())
                .surname(request.getSurname())
                .phone(request.getPhone())
                .department(request.getDepartment())
                .email(request.getEmail())
                .status(Boolean.TRUE)
                .age(request.getAge())
                .id(UUID.randomUUID())
                .build();
    }

    public void update(final EmployeeRequest request) {
        this.name = request.getName();
        this.surname = request.getSurname();
        this.phone = request.getPhone();
        this.department = request.getDepartment();
        this.email = request.getEmail();
        this.status = request.getStatus();
        this.age = request.getAge();
        this.address = Address.builder()
                .country(request.getCountry())
                .city(request.getCity())
                .region(request.getRegion())
                .district(request.getDistrict())
                .street(request.getStreet())
                .zipCode(request.getZipCode())
                .build();
    }

    public void partialUpdate(final Map<String, Object> fields) {
        // Yalnızca null olmayan alanları güncelle.
        if (fields.containsKey("name") && fields.get("name") != null) {
            this.setName((String) fields.get("name"));
        }
        if (fields.containsKey("email") && fields.get("email") != null) {
            this.setEmail((String) fields.get("email"));
        }
        if (fields.containsKey("phone") && fields.get("phone") != null) {
            this.setPhone((String) fields.get("phone"));
        }
        // Diğer alanlar için de benzer kontroller yapabilirsiniz.
    }
}
