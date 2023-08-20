package com.onurbas.controller;

import com.onurbas.dto.response.PostResponseDTO;
import com.onurbas.dto.request.PostRequestDTO;
import com.onurbas.model.Category;
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
  public ResponseEntity<PostResponseDTO> save(@RequestBody PostRequestDTO postRequestDTO,
											  @RequestParam Long userId,
											  @RequestParam(required = false) Long categoryId
											 ) {
	return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postRequestDTO,userId,categoryId));
  }

  @GetMapping(POST)
  public ResponseEntity<List<PostResponseDTO>> findAll() {
	return ResponseEntity.ok(postService.findAll());
  }

  @GetMapping(POST + "/{postId}")
  public ResponseEntity<PostResponseDTO> findById(@PathVariable(name = "postId") Long id) {
	return ResponseEntity.ok(postService.findById(id));
  }

  @PutMapping(POST + "/{postId}")
  public ResponseEntity<PostResponseDTO> update(@RequestBody PostResponseDTO postResponseDTO,@PathVariable(name = "postId") Long id) {

	return ResponseEntity.ok(postService.update(postResponseDTO,id,postResponseDTO.getUserId(),postResponseDTO.getCategoryId()));
  }

  @DeleteMapping(POST + "/{postId}")
  public ResponseEntity<Void> deleteById(@PathVariable(name = "postId") Long id) {
	postService.deleteById(id);
	return ResponseEntity.noContent().build();
  }

  @GetMapping(POST + "/category/{categoryId}")
  public ResponseEntity<List<PostResponseDTO>> findPostsByCategoryId(@PathVariable(name = "categoryId") Long id) {

	return ResponseEntity.ok(postService.findPostsByCategoryId(id));
  }

  @GetMapping(POST + "/user/{userId}")
  public ResponseEntity<List<PostResponseDTO>> findPostsByUserId(@PathVariable(name = "userId") Long id) {

	return ResponseEntity.ok(postService.findPostsByUserId(id));
  }

  @GetMapping(POST + "/orderbydate")
  public ResponseEntity<List<PostResponseDTO>> getPostsByOrderByDateDesc() {
	return ResponseEntity.ok(postService.getPostsByOrderByDateDesc());
  }

  @GetMapping("/api" + POST)
  public ResponseEntity<List<PostResponseDTO>> findPostsBySearch(
		  @RequestParam(name = "search", required = false) String search,
		  @RequestParam(name = "category", required = false) String category) {

	List<PostResponseDTO> result;

	if (search != null) {
	  result = postService.findPostsByContentContains(search);
	} else if (category != null) {
	  result = postService.findPostsByCategory(category);
	} else {

	  return ResponseEntity.badRequest().build();
	}

	return ResponseEntity.ok(result);
  }
}

