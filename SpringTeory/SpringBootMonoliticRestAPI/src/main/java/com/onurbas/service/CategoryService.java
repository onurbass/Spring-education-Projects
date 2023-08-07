package com.onurbas.service;

import com.onurbas.exception.BadRequestException;
import com.onurbas.exception.InternalServerErrorException;
import com.onurbas.exception.ResourceNotFoundException;
import com.onurbas.model.Category;
import com.onurbas.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final ICategoryRepository categoryRepository;

  public List<Category> findAll() {
	try {
	  return categoryRepository.findAll();
	} catch (Exception e) {
	  throw new InternalServerErrorException("An error occurred while fetching categories");
	}
  }

  public Category findById(Long id) {
	if (id <= 0) {
	  throw new BadRequestException("Invalid category ID: " + id);
	}

	Optional<Category> categoryOptional = categoryRepository.findById(id);
	if (categoryOptional.isEmpty()) {
	  throw new ResourceNotFoundException("Category not found with ID: " + id);
	}

	return categoryOptional.get();
  }

  public Category save(Category category) {
	try {
	  return categoryRepository.save(category);
	} catch (Exception e) {
	  throw new InternalServerErrorException("An error occurred while saving category");
	}
  }

  public void deleteById(Long id) {
	Optional<Category> category = categoryRepository.findById(id);
	try {
	  if (category.isEmpty()) {
		throw new ResourceNotFoundException("Category not found with ID: " + id);
	  }
	  categoryRepository.deleteById(id);
	} catch (Exception e) {
	  throw new InternalServerErrorException("An error occurred while deleting category");
	}
  }

}
