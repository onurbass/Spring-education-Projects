package com.trendyol.bootcampemployeemanagement.infrastructure.repository;

import com.trendyol.bootcampemployeemanagement.domain.Employee;
import com.trendyol.bootcampemployeemanagement.domain.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DefaultEmployeeRepository implements EmployeeRepository {

    private static Map<String, Employee> employeeDatabase = new HashMap<>();

    @Override
    public List<Employee> getEmployees() {
        return new ArrayList<>(employeeDatabase.values());
    }

    @Override
    public Employee getEmployeeById(final String id) {
        return employeeDatabase.get(id);
    }

    @Override
    public void save(final Employee employee) {
        employeeDatabase.put(employee.getId().toString(), employee);
    }

    @Override
    public void update(final Employee employee) {
        employeeDatabase.put(employee.getId().toString(), employee);
    }

    @Override
    public void delete(String id) {
        employeeDatabase.remove(id);
    }
}
