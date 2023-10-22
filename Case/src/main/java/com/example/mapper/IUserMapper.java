package com.example.mapper;

import com.example.dto.UserRequestDTO;
import com.example.dto.UserResponseDTO;
import com.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
  IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

  User toUser(UserRequestDTO userRequestDTO);

  UserResponseDTO toUserResponseDTO(User user);

  List<UserResponseDTO> toUserResponseDTOList(List<User> users);
}
