package com.socialmedia.utility;

import com.socialmedia.dto.response.UserProfileResponseDto;
import com.socialmedia.manager.IUserManager;
import com.socialmedia.mapper.IUserMapper;
import com.socialmedia.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllData {

    private final UserProfileService userProfileService;

    private final IUserManager userManager;

  //  @PostConstruct
    public void initData(){
    List<UserProfileResponseDto>list=userManager.findAllForElasticService().getBody();
        userProfileService.saveAll(IUserMapper.INSTANCE.toUserProfiles(list));
    }

}
