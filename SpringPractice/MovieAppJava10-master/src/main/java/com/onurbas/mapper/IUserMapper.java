package com.onurbas.mapper;

import com.onurbas.dto.request.RegisterRequestDto;
import com.onurbas.repository.entity.User;
import com.onurbas.response.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
  IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);
  UserResponseDto toUserResponseDto(User user);
  User toUser(RegisterRequestDto dto);
}
