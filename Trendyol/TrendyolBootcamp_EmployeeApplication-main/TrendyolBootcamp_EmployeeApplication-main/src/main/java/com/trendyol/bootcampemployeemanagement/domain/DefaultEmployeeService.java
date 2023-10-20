package com.trendyol.bootcampemployeemanagement.domain;

import com.trendyol.bootcampemployeemanagement.application.EmployeeService;
import com.trendyol.bootcampemployeemanagement.domain.exception.EmployeeNotFoundException;
import com.trendyol.bootcampemployeemanagement.interfaces.request.EmployeeRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DefaultEmployeeService implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public List<Employee> getEmployees() {
        return repository.getEmployees();
    }

    @Override
    public Employee getEmployee(final String id) {
        return repository.getEmployeeById(id);
    }

    @Override
    public Employee createEmployee(final EmployeeRequest request) {
        final Employee employee = Employee.createFrom(request);

        repository.save(employee);

        return employee;
    }

    @Override
    public void updateEmployee(final String id, final EmployeeRequest request) {
        final Employee employee = repository.getEmployeeById(id);

        if (employee == null) throw EmployeeNotFoundException.create(id);

        employee.update(request);

        repository.update(employee);
    }

    @Override
    public void patchEmployee(final String id, final Map<String, Object> fields) {
        final Employee employee = repository.getEmployeeById(id);

        if (employee == null) throw EmployeeNotFoundException.create(id);

        employee.partialUpdate(fields);

        repository.update(employee);
    }

    @Override
    public void deleteEmployee(final String id) {
        repository.delete(id);
    }
}
