package com.socialmedia.controller;

import com.socialmedia.repository.entity.UserProfile;
import com.socialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.socialmedia.constant.EndPoints.*;
/*
    findbystatus metodu yazıp cacheleme yapalım

 */
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(FIND_ALL)
    public ResponseEntity<Iterable<UserProfile>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }


}
