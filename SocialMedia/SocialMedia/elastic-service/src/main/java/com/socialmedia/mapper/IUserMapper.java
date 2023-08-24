package com.socialmedia.mapper;



import com.socialmedia.dto.response.UserProfileResponseDto;

import com.socialmedia.graphql.model.UserProfileInput;
import com.socialmedia.rabbitmq.model.RegisterElasticModel;
import com.socialmedia.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);


    List<UserProfile> toUserProfiles(List<UserProfileResponseDto> userProfileResponseDtos);

    UserProfile toUserProfile(RegisterElasticModel model);

    UserProfile toUserProfile(UserProfileInput userProfileInput);

}
