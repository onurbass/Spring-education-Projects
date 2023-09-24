package com.onurbas.mapper;



import com.onurbas.dto.request.UserProfileUpdateRequestDto;
import com.onurbas.dto.request.UserSaveRequestDto;
import com.onurbas.dto.response.UserProfileFindAllResponseDto;
import com.onurbas.rabbitmq.model.RegisterElasticModel;
import com.onurbas.rabbitmq.model.RegisterModel;
import com.onurbas.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    UserProfile toUserProfile(UserSaveRequestDto dto);
    UserProfile toUserProfile(RegisterModel model);


    UserProfile toUserProfile(UserProfileUpdateRequestDto dto);

   // @Mapping( source = "id",target = "userProfileId")
    UserProfileFindAllResponseDto toUserProfileFindAllResponseDto(UserProfile userProfile);

    RegisterElasticModel toRegisterElasticModel(UserProfile userProfile);


}
