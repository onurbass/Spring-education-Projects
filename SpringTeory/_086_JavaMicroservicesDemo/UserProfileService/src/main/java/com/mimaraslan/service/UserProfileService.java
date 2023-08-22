package com.mimaraslan.service;

import com.mimaraslan.dto.request.UserProfileSaveRequestDto;
import com.mimaraslan.mapper.IUserProfileMapper;
import com.mimaraslan.repository.IUserProfileRepository;
import com.mimaraslan.repository.entity.UserProfile;
import com.mimaraslan.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager <UserProfile, Long> {

    private final IUserProfileRepository repository;

    public UserProfileService(IUserProfileRepository repository) {
        super(repository);
        this.repository = repository;
    }


    public Boolean saveDto(UserProfileSaveRequestDto dto) {
        /*UserProfile userProfile = UserProfile.builder()
                .authid(dto.getAuthId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build();*/
        UserProfile userProfile = IUserProfileMapper.INSTANCE.toUserProfileSaveRequestDto(dto);
        save(userProfile);
        return  true;
    }
}
