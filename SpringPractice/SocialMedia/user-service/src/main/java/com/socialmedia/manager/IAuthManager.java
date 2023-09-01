package com.socialmedia.manager;

import com.socialmedia.dto.request.AuthUpdateRequestDto;
import com.socialmedia.dto.request.UserSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static com.socialmedia.constant.EndPoint.*;

@FeignClient(
        url = "http://localhost:7071/api/v1/auth", decode404 = true, name = "userprofile-auth"
)
public interface IAuthManager {

    @PutMapping(UPDATE)
    ResponseEntity<String> updateAuth(@RequestBody AuthUpdateRequestDto dto);

}
