package com.onurbas.controller;

import com.onurbas.model.Post;
import com.onurbas.model.Post;
import com.onurbas.model.User;
import com.onurbas.service.PostService;
import com.onurbas.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.onurbas.constant.RestApiUrl.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @GetMapping(POST)
  public ResponseEntity<List<Post>> findAll() {
	return ResponseEntity.ok(postService.findAll());
  }

  @GetMapping(POST + "/{postId}")
  public ResponseEntity<Post> findById(@PathVariable(name = "postId") Long id) {
	return ResponseEntity.ok(postService.findById(id));
  }

  @PostMapping(POST)
  public ResponseEntity<Post> save(@RequestBody Post post) {
	return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(post));
  }

  @DeleteMapping(POST + "/{postId}")
  public ResponseEntity<Void> deleteById(@PathVariable(name = "postId") Long id) {
	postService.deleteById(id);
	return ResponseEntity.noContent().build();
  }

  @GetMapping(POST + "/user/{userId}")
  public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable(name = "userId") Long id) {

	return ResponseEntity.ok(postService.getPostsByUserId(id));
  }

  @PutMapping(POST /*+ "/{postId}"*/)
  public ResponseEntity<Post> update(@RequestBody Post post) {
	return ResponseEntity.ok(postService.save(post));
  }

  @GetMapping(POST + "/category/{categoryId}")
  public ResponseEntity<List<Post>> getPostsByCategoryId(@PathVariable(name = "categoryId") Long id) {

	return ResponseEntity.ok(postService.getPostsByCategoryId(id));
  }

}
