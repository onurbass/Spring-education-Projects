package com.trendyol.bootcampemployeemanagement.domain.exception;

public class EmployeeNotFoundException extends RuntimeException {
    private String id;

    private EmployeeNotFoundException(String id) {
        this.id = id;
    }

    public static EmployeeNotFoundException create(String id) {
        return new EmployeeNotFoundException(id);
    }

    @Override
    public String toString() {
        return "Employee with id: " + this.id + " does not exist in system.";
    }
}
