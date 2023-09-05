package com.socialmedia.utility;

import com.socialmedia.dto.response.UserProfileFindAllResponseDto;
import com.socialmedia.manager.IUserManager;
import com.socialmedia.mapper.IElasticMapper;
import com.socialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllData {

private final IUserManager userManager;

private final UserService userService;

//@PostConstruct
public void initData(){
   List<UserProfileFindAllResponseDto> list=userManager.findAll().getBody();
    //List<UserProfile>
   userService.saveAll(IElasticMapper.INSTANCE.toUserProfiles(list));
}

}
