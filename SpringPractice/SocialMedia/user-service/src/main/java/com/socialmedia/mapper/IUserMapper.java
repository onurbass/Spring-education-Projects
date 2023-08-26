package com.socialmedia.mapper;

import com.socialmedia.dto.UpdateRequestDto;
import com.socialmedia.dto.UserSaveRequestDto;
import com.socialmedia.dto.UserUpdateRequestDto;
import com.socialmedia.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

  IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

  UserProfile toUserProfile(UserSaveRequestDto dto);

  UserProfile toUserProfile(UserUpdateRequestDto dto);

  UpdateRequestDto toUpdateRequestDto(UserProfile userProfile);
}
