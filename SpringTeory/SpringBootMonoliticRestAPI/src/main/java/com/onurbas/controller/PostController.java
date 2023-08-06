package com.onurbas.controller;

import com.onurbas.model.Post;
import com.onurbas.service.PostService;
import com.onurbas.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.onurbas.constant.RestApiUrl.POST;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;
  

  @GetMapping(POST)
  public List<Post> findAll() {
    return postService.findAll();
  }

  @GetMapping(POST + "/{postId}")
  public Post findById(@PathVariable(name = "postId") Long id) {
    return postService.findById(id);
  }

  @PostMapping(POST)
  public Post save(@RequestBody Post post) {
    return postService.save(post);
  }

  @DeleteMapping(POST + "/{postId}")
  public void deleteById(@PathVariable(name = "postId") Long id) {
    postService.deleteById(id);
  }

}
