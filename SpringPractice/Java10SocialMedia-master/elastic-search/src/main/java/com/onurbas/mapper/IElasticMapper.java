package com.onurbas.mapper;

import com.onurbas.dto.response.UserProfileFindAllResponseDto;
import com.onurbas.rabbitmq.model.RegisterElasticModel;
import com.onurbas.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IElasticMapper {
    IElasticMapper INSTANCE = Mappers.getMapper(IElasticMapper.class);

    List<UserProfile> toUserProfiles(List<UserProfileFindAllResponseDto> dtos);

    UserProfile toUserProfile(RegisterElasticModel model);
}
