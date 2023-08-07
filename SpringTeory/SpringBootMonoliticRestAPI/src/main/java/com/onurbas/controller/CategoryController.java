package com.onurbas.controller;

import com.onurbas.model.Category;
import com.onurbas.model.Post;
import com.onurbas.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.onurbas.constant.RestApiUrl.CATEGORY;
import static com.onurbas.constant.RestApiUrl.POST;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping(CATEGORY)
  public ResponseEntity<List<Category>> findAll() {
	return ResponseEntity.ok(categoryService.findAll());
  }

  @GetMapping(CATEGORY + "/{categoryId}")
  public ResponseEntity<Category> findById(@PathVariable(name = "categoryId") Long id) {
	return ResponseEntity.ok(categoryService.findById(id));
  }

  @PostMapping(CATEGORY)
  public ResponseEntity<Category> save(@RequestBody Category category) {
	return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
  }

  @PutMapping(CATEGORY /*+ "/{categoryId}"*/)
  public ResponseEntity<Category> update(@RequestBody Category category) {
	return ResponseEntity.ok(categoryService.save(category));
  }

  @DeleteMapping(CATEGORY + "/{categoryId}")
  public ResponseEntity<Void> deleteById(@PathVariable(name = "categoryId") Long id) {
	categoryService.deleteById(id);
	return ResponseEntity.noContent().build();
  }


}
