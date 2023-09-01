package com.socialmedia.mapper;

import com.socialmedia.dto.request.AuthUpdateRequestDto;
import com.socialmedia.dto.request.UserProfileUpdateRequestDto;
import com.socialmedia.dto.request.UserSaveRequestDto;
import com.socialmedia.rabbitmq.model.RegisterModel;
import com.socialmedia.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    UserProfile toUserProfile(UserSaveRequestDto dto);
    UserProfile toUserProfile(RegisterModel model);

    UserProfile toUserProfile(UserProfileUpdateRequestDto dto);
    AuthUpdateRequestDto toAuthUpdateRequestDto(UserProfile userProfile);

}
