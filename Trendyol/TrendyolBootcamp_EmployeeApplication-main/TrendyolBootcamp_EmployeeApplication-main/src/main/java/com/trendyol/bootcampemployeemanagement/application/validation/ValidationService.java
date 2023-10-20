package com.trendyol.bootcampemployeemanagement.application.validation;

public interface ValidationService<T> {
    ValidationResult validate(T request);
}