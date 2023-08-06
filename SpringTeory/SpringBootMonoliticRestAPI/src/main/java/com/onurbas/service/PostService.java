package com.onurbas.service;

import com.onurbas.model.Post;
import com.onurbas.repository.IPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
  private final IPostRepository postRepository;

  public List<Post> findAll(){
	return postRepository.findAll();
  }
  public Post findById(Long id){
	Optional<Post> post=postRepository.findById(id);
	if (post.isEmpty()){
	  throw new RuntimeException("Kullanıcı bulunamadı");
	}
	return post.get();
  }
  public Post save(Post post){
	return postRepository.save(post);
  }
  public void deleteById(Long id){
	postRepository.deleteById(id);
  }


}
