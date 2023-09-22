package com.bilgeadam.controller;


import com.bilgeadam.dto.response.UserProfileFindAllResponseDto;
import com.bilgeadam.repository.entity.Post;
import com.bilgeadam.repository.enums.EStatus;
import com.bilgeadam.service.PostService;
import com.bilgeadam.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.bilgeadam.constant.EndPoints.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class CommentController {
    private final PostService userService;
    private final JwtTokenManager jwtTokenManager;

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(@RequestBody UserSaveRequestDto dto,@RequestHeader("Authorization") String token){
        System.out.println("Authdan gelen token=>"+token);
        return ResponseEntity.ok(userService.createNewUser(dto));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<String> updateUserProfile(@RequestBody @Valid UserUpdateRequestDto dto){
        return  ResponseEntity.ok(userService.updateUserProfile(dto));
    }
    @PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<String> activateStatus(@RequestParam String token){
        return ResponseEntity.ok(userService.activateStatus(token));
    }

    @GetMapping(FIND_ALL)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserProfileFindAllResponseDto>> findAll(){
        return ResponseEntity.ok(userService.findAllUserProfile());
    }
    @GetMapping("/find_by_username/{username}")
    public ResponseEntity<Post> findByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.findByUsername(username));
    }
    @GetMapping("/find_by_status/{status}")
    public ResponseEntity<List<Post>> findUserProfileByStatus(@PathVariable EStatus status){
        return ResponseEntity.ok(userService.findUserProfileByStatus(status));
    }
    @DeleteMapping(DELETE_BY_ID)
    public ResponseEntity<String> deleteById(@RequestHeader("Authorization") String token){
       return ResponseEntity.ok(userService.deleteUserProfile(token));
    }
    @GetMapping("/find_all_by_pageable")
    public ResponseEntity<Page<Post>> findAllByPageable(int pageSize, @RequestParam(required = false,defaultValue = "ASC") int pageNumber, String direction, @RequestParam(required = false,defaultValue = "id") String sortParameter){
        return ResponseEntity.ok(userService.findAllByPageable(pageSize,pageNumber,direction,sortParameter));
    }
    @GetMapping("/find_all_by_slice")
    public ResponseEntity<Slice<Post>> findAllBySlice(int pageSize, @RequestParam(required = false,defaultValue = "ASC") int pageNumber, String direction, @RequestParam(required = false,defaultValue = "id") String sortParameter){
        return ResponseEntity.ok(userService.findAllBySlice(pageSize,pageNumber,direction,sortParameter));
    }
}
