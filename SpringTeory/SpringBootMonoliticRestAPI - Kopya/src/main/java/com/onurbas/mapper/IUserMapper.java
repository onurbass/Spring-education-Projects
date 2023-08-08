package com.onurbas.mapper;

import com.onurbas.dto.request.RegisterRequestDto;
import com.onurbas.dto.response.UserDTO;
import com.onurbas.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

  IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

  UserDTO userToUserDto(User user);

  List<UserDTO> userListToUserDTOList(List<User> userList);
  User userDTOToUser(UserDTO userDTO);
  List<User> userDTOListToUserList(List<UserDTO> userDTOListList);

  User registerRequestDTOToUser(RegisterRequestDto dto);

}
