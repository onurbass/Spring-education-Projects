package com.socialmedia.controller;


import com.socialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.socialmedia.constant.EndPoint.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final JwtTokenManager jwtTokenManager;

}
