package com.onurbas.utility;

import com.onurbas.dto.response.UserProfileResponseDto;
import com.onurbas.manager.IUserManager;
import com.onurbas.mapper.IUserMapper;
import com.onurbas.service.UserProfileService;
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
