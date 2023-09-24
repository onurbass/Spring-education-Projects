package com.onurbas.controller;

import com.onurbas.service.LikeService;
import com.onurbas.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.onurbas.constant.EndPoints.LIKE;

@RestController
@RequestMapping(LIKE)
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private final JwtTokenManager jwtTokenManager;

}
