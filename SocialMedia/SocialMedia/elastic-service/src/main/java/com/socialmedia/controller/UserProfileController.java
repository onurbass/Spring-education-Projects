package com.socialmedia.controller;

import com.socialmedia.repository.entity.UserProfile;
import com.socialmedia.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import  static com.socialmedia.constant.EndPoints.*;
@RequiredArgsConstructor
@RestController
@RequestMapping(USER)
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping(FINDALL)
    public ResponseEntity<Iterable<UserProfile>> findAll(){
        return  ResponseEntity.ok( userProfileService.findAll());
    }

    @GetMapping("/findallbypageable")
    public ResponseEntity<Page<UserProfile>> findAllByPageable(int pageSize, int pageNumber,
                                                               @RequestParam(required = false,defaultValue = "ASC") String direction,
                                                               @RequestParam(required = false,defaultValue = "id") String sortParameter){

        return  ResponseEntity.ok(userProfileService.findAllByPageable(pageSize,pageNumber,direction,sortParameter));
    }
}