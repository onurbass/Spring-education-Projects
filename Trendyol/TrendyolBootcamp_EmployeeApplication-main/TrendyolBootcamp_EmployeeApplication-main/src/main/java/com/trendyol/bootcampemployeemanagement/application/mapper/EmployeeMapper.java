package com.trendyol.bootcampemployeemanagement.application.mapper;

import com.trendyol.bootcampemployeemanagement.domain.Address;
import com.trendyol.bootcampemployeemanagement.domain.Employee;
import com.trendyol.bootcampemployeemanagement.interfaces.response.EmployeeResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {
    public EmployeeResponse employeeToResponse(Employee employee) {
        return EmployeeResponse.builder()
                .name(employee.getName())
                .surname(employee.getSurname())
                .id(employee.getId())
                .department(employee.getDepartment())
                .phone(employee.getPhone())
                .email(employee.getEmail())
                .status(employee.getStatus())
                .age(employee.getAge())
                .address(Address
                        .builder()
                        .region(employee.getAddress()
                                .getRegion())
                        .city(employee.getAddress().getCity())
                        .country(employee.getAddress().getCountry())
                        .street(employee.getAddress().getStreet())
                        .district(employee.getAddress().getDistrict())
                        .zipCode(employee.getAddress().getZipCode())
                        .build())
                .build();
    }

    public List<EmployeeResponse> employeeListToResponseList(List<Employee> employees) {
        return employees.stream().map(this::employeeToResponse).collect(Collectors.toList());
    }
}
