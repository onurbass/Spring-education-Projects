package com.onurbas.utility;

import com.onurbas.dto.response.UserProfileFindAllResponseDto;
import com.onurbas.manager.IUserManager;
import com.onurbas.mapper.IElasticMapper;
import com.onurbas.service.UserService;
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
