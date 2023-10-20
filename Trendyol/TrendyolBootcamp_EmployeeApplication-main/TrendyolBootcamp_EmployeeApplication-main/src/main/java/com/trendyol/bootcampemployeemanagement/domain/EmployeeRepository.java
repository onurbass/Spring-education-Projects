package com.trendyol.bootcampemployeemanagement.domain;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> getEmployees();

    Employee getEmployeeById(final String id);

    void save(final Employee employee);

    void update(final Employee employee);

    void delete(final String id);
}
