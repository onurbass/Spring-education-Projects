package com.onurbas.service;

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

  public List<Category> findAll(){
	return categoryRepository.findAll();
  }
  public Category findById(Long id){
	Optional<Category> category=categoryRepository.findById(id);
	if (category.isEmpty()){
	  throw new RuntimeException("Kullanıcı bulunamadı");
	}
	return category.get();
  }
  public Category save(Category category){
	return categoryRepository.save(category);
  }
  public void deleteById(Long id){
	categoryRepository.deleteById(id);
  }


}
