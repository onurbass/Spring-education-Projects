package com.onurbas.service;

import com.onurbas.dto.response.PostDTO;
import com.onurbas.exception.BadRequestException;
import com.onurbas.exception.InternalServerErrorException;
import com.onurbas.exception.ResourceNotFoundException;
import com.onurbas.mapper.IPostMapper;
import com.onurbas.model.Post;
import com.onurbas.repository.IPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
  private final IPostRepository postRepository;

  public List<PostDTO> findAll() {
	try {
	  return IPostMapper.INSTANCE.postListToPostDTOList(postRepository.findAll());
	} catch (DataAccessException e) {
	  throw new InternalServerErrorException("An error occurred while fetching categories");
	}
  }

  public PostDTO findById(Long id) {
	if (id <= 0) {
	  throw new BadRequestException("Invalid post ID: " + id);
	}

	Optional<Post> postOptional = postRepository.findById(id);
	if (postOptional.isEmpty()) {
	  throw new ResourceNotFoundException("Post not found with ID: " + id);
	}
	PostDTO postDTO = IPostMapper.INSTANCE.postToPostDTO(postRepository.findById(id).get());
	return postDTO;
  }

  public PostDTO save(Post post) {
	try {
	  if (post == null) {
		throw new BadRequestException("Post cannot be null");
	  }
	  PostDTO postDto = IPostMapper.INSTANCE.postToPostDTO(postRepository.save(post));
	  return postDto;
	} catch (Exception e) {
	  throw new InternalServerErrorException("An error occurred while saving post");
	}
  }

  public void deleteById(Long id) {
	Optional<Post> post = postRepository.findById(id);
	try {
	  if (post.isEmpty()) {
		throw new ResourceNotFoundException("Post not found with ID: " + id);
	  }
	  postRepository.deleteById(id);
	} catch (Exception e) {
	  throw new InternalServerErrorException("An error occurred while deleting post");
	}
  }

  public List<PostDTO> findPostsByUserId(Long id) {
	List<PostDTO> posts = IPostMapper.INSTANCE.postListToPostDTOList(postRepository.findPostsByUserId(id));
	if (posts.isEmpty()) {
	  throw new ResourceNotFoundException("No posts found for user with id: " + id);
	}

	return posts;
  }

  public List<PostDTO> findPostsByCategoryId(Long id) {
	List<PostDTO> posts = IPostMapper.INSTANCE.postListToPostDTOList(postRepository.findPostsByCategoryId(id));
	if (posts.isEmpty()) {
	  throw new ResourceNotFoundException("No posts found for category with id: " + id);
	}
	return posts;
  }

}
