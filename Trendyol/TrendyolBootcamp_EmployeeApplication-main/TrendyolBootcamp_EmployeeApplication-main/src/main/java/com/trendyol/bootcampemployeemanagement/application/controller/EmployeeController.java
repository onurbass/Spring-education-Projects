package com.trendyol.bootcampemployeemanagement.application.controller;

import com.trendyol.bootcampemployeemanagement.application.EmployeeService;
import com.trendyol.bootcampemployeemanagement.application.mapper.EmployeeMapper;
import com.trendyol.bootcampemployeemanagement.domain.Employee;
import com.trendyol.bootcampemployeemanagement.interfaces.request.EmployeeRequest;
import com.trendyol.bootcampemployeemanagement.interfaces.response.EmployeeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper mapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper mapper) {
        this.employeeService = employeeService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody @Valid EmployeeRequest request) {
        final Employee employee = employeeService.createEmployee(request);
        return new ResponseEntity<>(mapper.employeeToResponse(employee), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getEmployees() {
        final List<Employee> employees = employeeService.getEmployees();
        return new ResponseEntity<>(mapper.employeeListToResponseList(employees), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable String id) {
        final Employee employee = employeeService.getEmployee(id);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(mapper.employeeToResponse(employee), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable String id, @Valid @RequestBody EmployeeRequest request) {
        employeeService.updateEmployee(id, request);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> patchEmployee(@PathVariable String id, @RequestBody Map<String, Object> fields) {
        employeeService.patchEmployee(id, fields);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/trace", method = RequestMethod.TRACE)
    public ResponseEntity<String> trace() {
        return new ResponseEntity<>("TRACE response", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> headEmployee(@PathVariable String id) {
        final Employee employee = employeeService.getEmployee(id);
        if (employee != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> options(HttpServletResponse response) {
        response.setHeader("Allow", "GET,HEAD,POST,PUT,DELETE,OPTIONS");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
