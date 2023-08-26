package com.socialmedia.manager;

import com.socialmedia.dto.UpdateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.socialmedia.constant.EndPoints.*;

@FeignClient(url = "http://localhost:7070/api/v1/auth",decode404 = true,name = "userprofile-auth")
public interface IAuthManager {
  @PutMapping(UPDATE)
   ResponseEntity<Boolean> updateAuth(@RequestBody UpdateRequestDto dto);

}
