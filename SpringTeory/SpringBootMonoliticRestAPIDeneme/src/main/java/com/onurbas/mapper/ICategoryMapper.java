package com.onurbas.mapper;

import com.onurbas.dto.response.CategoryDTO;
import com.onurbas.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryMapper {
  ICategoryMapper INSTANCE = Mappers.getMapper(ICategoryMapper.class);

  CategoryDTO categoryToCategoryDTO(Category category);

  List<CategoryDTO> categoryListToCategoryDTOList(List<Category> category);

  Category categoryDTOToCategory(CategoryDTO categoryDTO);

  List<Category> categoryDTOListToCategoryList(List<CategoryDTO> categoryDTOList);
}