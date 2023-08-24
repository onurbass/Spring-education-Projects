package com.socialmedia.manager;

import com.socialmedia.dto.request.UserSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.socialmedia.constant.EndPoints.ACTIVATION;
import static com.socialmedia.constant.EndPoints.SAVE;


@FeignClient(url = "${feign.user}",decode404 = true,name = "auth-userprofile")
public interface IUserManager {

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> createNewUser(@RequestBody UserSaveRequestDto dto ,@RequestHeader("Authorization") String token);

    @PostMapping(ACTIVATION+"/{authId}")
    public  ResponseEntity<String> activateUser(@PathVariable Long authId);

    @PostMapping(ACTIVATION)
    public  ResponseEntity<String> activateUser2(@RequestParam Long authId);
}
