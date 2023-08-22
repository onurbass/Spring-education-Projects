package com.onurbas.controller;

import com.onurbas.dto.CategoryDTO;
import com.onurbas.model.Category;
import com.onurbas.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.onurbas.constant.RestApiUrl.CATEGORY;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping(CATEGORY)
  public ResponseEntity<List<CategoryDTO>> findAll() {
	return ResponseEntity.ok(categoryService.findAll());
  }

  @GetMapping(CATEGORY + "/{categoryId}")
  public ResponseEntity<CategoryDTO> findById(@PathVariable(name = "categoryId") Long id) {
	return ResponseEntity.ok(categoryService.findById(id));
  }

  @PostMapping(CATEGORY)
  public ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO categoryDTO) {
	return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryDTO));
  }

  @PutMapping(CATEGORY + "/{categoryId}")
  public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO categoryDTO,@PathVariable(name = "categoryId") Long id) {

	return ResponseEntity.ok(categoryService.update(categoryDTO,id));
  }

  @DeleteMapping(CATEGORY + "/{categoryId}")
  public ResponseEntity<Void> deleteById(@PathVariable(name = "categoryId") Long id) {
	categoryService.deleteById(id);
	return ResponseEntity.noContent().build();
  }
  @GetMapping(CATEGORY + "/findbyname/{name}")
  public CategoryDTO findCategoryByCategoryNameIgnoreCase(@PathVariable(name = "name") String name){

	return categoryService.findCategoryByCategoryNameIgnoreCase(name);
  }

}
