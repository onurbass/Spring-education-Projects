package com.onurbas.mapper;

import com.onurbas.dto.response.UserResponseDTO;
import com.onurbas.dto.request.UserRequestDTO;
import com.onurbas.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

  IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

  UserResponseDTO userToUserDto(User user);

  List<UserResponseDTO> userListToUserDTOList(List<User> userList);

  User userDTOToUser(UserResponseDTO userResponseDTO);

  UserRequestDTO userToUserRequestDTO(User user);

  User userRequestDTOToUser(UserRequestDTO userRequestDTO);

  List<User> userDTOListToUserList(List<UserResponseDTO> userResponseDTOListList);

}
