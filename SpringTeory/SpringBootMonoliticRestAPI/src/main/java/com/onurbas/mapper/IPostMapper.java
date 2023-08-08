package com.onurbas.mapper;

import com.onurbas.dto.response.PostResponseDTO;
import com.onurbas.dto.request.PostRequestDTO;
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
  PostResponseDTO postToPostDTO(Post post);

  @Mapping(source = "user.id", target = "userId")
  @Mapping(source = "category.id", target = "categoryId")
  List<PostResponseDTO> postListToPostDTOList(List<Post> postList);


  @Mapping(source = "userId", target = "user.id")
  @Mapping(source = "categoryId", target = "category.id")
  Post postDTOToPost(PostResponseDTO postResponseDTO);

  Post postRequestDTOToPost(PostRequestDTO postRequestDTO);
  PostRequestDTO postToPostRequestDTO(Post post);

  @Mapping(source = "userId", target = "user.id")
  @Mapping(source = "categoryId", target = "category.id")
  List<Post> postDTOListToPostList(List<PostResponseDTO> postResponseDTOList);

}