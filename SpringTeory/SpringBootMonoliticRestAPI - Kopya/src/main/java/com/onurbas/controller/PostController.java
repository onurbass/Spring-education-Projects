package com.onurbas.controller;

import com.onurbas.dto.response.PostDTO;
import com.onurbas.mapper.IPostMapper;
import com.onurbas.model.Post;
import com.onurbas.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.onurbas.constant.RestApiUrl.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @PostMapping(POST)
  public ResponseEntity<PostDTO> save(@RequestBody Post post,
									  Long userId,
									  Long categoryId) {
	return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(post,userId,categoryId));
  }

  @GetMapping(POST)
  public ResponseEntity<List<PostDTO>> findAll() {
	return ResponseEntity.ok(postService.findAll());
  }

  @GetMapping(POST + "/{postId}")
  public ResponseEntity<PostDTO> findById(@PathVariable(name = "postId") Long id) {
	return ResponseEntity.ok(postService.findById(id));
  }

  @PutMapping(POST + "/{postId}")
  public ResponseEntity<PostDTO> update(@RequestBody Post post,@PathVariable(name = "postId") Long id) {
	post.setId(id);
	return ResponseEntity.ok(postService.save(post,post.getUser().getId(),post.getCategory().getId()));
  }

  @DeleteMapping(POST + "/{postId}")
  public ResponseEntity<Void> deleteById(@PathVariable(name = "postId") Long id) {
	postService.deleteById(id);
	return ResponseEntity.noContent().build();
  }

  @GetMapping(POST + "/category/{categoryId}")
  public ResponseEntity<List<PostDTO>> findPostsByCategoryId(@PathVariable(name = "categoryId") Long id) {

	return ResponseEntity.ok(postService.findPostsByCategoryId(id));
  }

  @GetMapping(POST + "/user/{userId}")
  public ResponseEntity<List<PostDTO>> findPostsByUserId(@PathVariable(name = "userId") Long id) {

	return ResponseEntity.ok(postService.findPostsByUserId(id));
  }
}
