package com.onurbas.mapper;

import com.onurbas.dto.response.PostDTO;
import com.onurbas.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPostMapper {
  IPostMapper INSTANCE = Mappers.getMapper(IPostMapper.class);

  @Mapping(source = "user.id", target = "userId")
  @Mapping(source = "category.id", target = "categoryId")
  PostDTO postToPostDTO(Post post);

  @Mapping(source = "user.id", target = "userId")
  @Mapping(source = "category.id", target = "categoryId")
  List<PostDTO> postListToPostDTOList(List<Post> postList);

}