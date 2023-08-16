package com.onurbas.mapper;


import com.onurbas.dto.request.UpdateRequestDto;
import com.onurbas.dto.request.UserProfileUpdateRequestDto;
import com.onurbas.dto.request.UserSaveRequestDto;
import com.onurbas.dto.response.UserProfileResponseDto;
import com.onurbas.rabbitmq.model.RegisterElasticModel;
import com.onurbas.rabbitmq.model.RegisterModel;
import com.onurbas.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);


    UserProfile toUserProfile(final UserSaveRequestDto dto);


    UserProfile toUserProfile(UserProfileUpdateRequestDto dto);
    UserProfile toUserProfile(RegisterModel model);
    @Mapping(source = "authId" ,target = "id")
    UpdateRequestDto toUpdateRequestDto(final UserProfile userProfile);

   // @Mapping(source = "id",target = "userProfileId")
    UserProfileResponseDto toUserProfileResponseDto(UserProfile userProfile);

    UserProfile toUserProfile(RegisterElasticModel model);

   // @Mapping(source = "id",target = "userProfileId")
    RegisterElasticModel toRegisterElasticModel(UserProfile userProfile);

}
