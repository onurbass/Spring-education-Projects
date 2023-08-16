package com.onurbas.mapper;



import com.onurbas.dto.response.UserProfileResponseDto;

import com.onurbas.graphql.model.UserProfileInput;
import com.onurbas.rabbitmq.model.RegisterElasticModel;
import com.onurbas.repository.entity.UserProfile;
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
