package com.onurbas.controller;

import com.onurbas.service.CommentService;
import com.onurbas.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.onurbas.constant.EndPoints.COMMENT;

@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final JwtTokenManager jwtTokenManager;

}
