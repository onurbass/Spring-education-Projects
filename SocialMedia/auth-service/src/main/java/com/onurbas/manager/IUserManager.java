package com.onurbas.manager;

import com.onurbas.dto.request.UserSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.onurbas.constant.EndPoints.ACTIVATION;
import static com.onurbas.constant.EndPoints.SAVE;


@FeignClient(url = "${feign.user}",decode404 = true,name = "auth-userprofile")
public interface IUserManager {

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> createNewUser(@RequestBody UserSaveRequestDto dto ,@RequestHeader("Authorization") String token);

    @PostMapping(ACTIVATION+"/{authId}")
    public  ResponseEntity<String> activateUser(@PathVariable Long authId);

    @PostMapping(ACTIVATION)
    public  ResponseEntity<String> activateUser2(@RequestParam Long authId);
}
