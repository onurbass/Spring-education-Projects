package com.trendyol.bootcampemployeemanagement.application;

import com.trendyol.bootcampemployeemanagement.domain.Employee;
import com.trendyol.bootcampemployeemanagement.interfaces.request.EmployeeRequest;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<Employee> getEmployees();

    Employee getEmployee(final String id);

    Employee createEmployee(final EmployeeRequest request);

    void updateEmployee(final String id, final EmployeeRequest request);

    void patchEmployee(final String id, final Map<String, Object> fields);

    void deleteEmployee(final String id);


}
