package com.onurbas.service;

import com.onurbas.exception.BadRequestException;
import com.onurbas.exception.InternalServerErrorException;
import com.onurbas.exception.ResourceNotFoundException;
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

  public List<Post> findAll() {
	try {
	  return postRepository.findAll();
	} catch (DataAccessException e) {
	  throw new InternalServerErrorException("An error occurred while fetching categories");
	}
  }

  public Post findById(Long id) {
	if (id <= 0) {
	  throw new BadRequestException("Invalid post ID: " + id);
	}

	Optional<Post> postOptional = postRepository.findById(id);
	if (postOptional.isEmpty()) {
	  throw new ResourceNotFoundException("Post not found with ID: " + id);
	}

	return postOptional.get();
  }

  public Post save(Post post) {
	try {
	  return postRepository.save(post);
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

  public List<Post> getPostsByUserId(Long id) {
	List<Post> posts = postRepository.getPostsByUserId(id);
	if (posts.isEmpty()) {
	  throw new ResourceNotFoundException("No posts found for user with id: " + id);
	}

	return posts;
  }

  public List<Post> getPostsByCategoryId(Long id) {
	List<Post> posts = postRepository.getPostsByUserId(id);
	if (posts.isEmpty()) {
	  throw new ResourceNotFoundException("No posts found for category with id: " + id);
	}
	return posts;
  }

}
