package com.onurbas.controller;

import com.onurbas.model.Category;
import com.onurbas.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.onurbas.constant.RestApiUrl.CATEGORY;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;


  @GetMapping(CATEGORY)
  public List<Category> findAll() {
    return categoryService.findAll();
  }

  @GetMapping(CATEGORY + "/{categoryId}")
  public Category findById(@PathVariable(name = "categoryId") Long id) {
    return categoryService.findById(id);
  }

  @PostMapping(CATEGORY)
  public Category save(@RequestBody Category category) {
    return categoryService.save(category);
  }

  @DeleteMapping(CATEGORY + "/{categoryId}")
  public void deleteById(@PathVariable(name = "categoryId") Long id) {
    categoryService.deleteById(id);
  }

}
