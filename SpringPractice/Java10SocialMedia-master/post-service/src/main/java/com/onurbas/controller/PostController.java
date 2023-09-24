package com.onurbas.controller;

import com.onurbas.dto.request.CreateNewPostRequestDto;
import com.onurbas.repository.entity.Post;
import com.onurbas.service.PostService;
import com.onurbas.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.onurbas.constant.EndPoints.*;

@RestController
@RequestMapping(POST)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final JwtTokenManager jwtTokenManager;

    @PostMapping("/create-post/{token}")
    public ResponseEntity<Post> createPost(@PathVariable String token, @RequestBody CreateNewPostRequestDto dto){
        return ResponseEntity.ok(postService.createPost(token, dto));
    }
}
