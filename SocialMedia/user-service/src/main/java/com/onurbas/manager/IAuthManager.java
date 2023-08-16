package com.onurbas.manager;

import com.onurbas.dto.request.UpdateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static com.onurbas.constant.EndPoints.UPDATE;

@FeignClient(url = "${feign.auth}",decode404 = true,name = "user-auth")
public interface IAuthManager {

    @PutMapping(UPDATE)
    public ResponseEntity<String> update(@RequestBody UpdateRequestDto dto, @RequestHeader("Authorization")String token);
}
