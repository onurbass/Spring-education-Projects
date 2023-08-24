package com.socialmedia.manager;

import com.socialmedia.dto.response.UserProfileResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.socialmedia.constant.EndPoints.FINDALL;

@FeignClient(url = "http://localhost:7072/api/v1/user",decode404 = true,name = "elastic-userprofile")
public interface IUserManager {


    @GetMapping(FINDALL+"forelastic")
    public ResponseEntity<List<UserProfileResponseDto>> findAllForElasticService();
}
