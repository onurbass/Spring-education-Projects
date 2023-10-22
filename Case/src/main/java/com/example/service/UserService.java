package com.example.service;

import com.example.dto.UserRequestDTO;
import com.example.dto.UserResponseDTO;
import com.example.exception.ErrorType;
import com.example.exception.UserException;
import com.example.mapper.IUserMapper;
import com.example.model.User;
import com.example.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final IUserRepository userRepository;

  public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
	User user = IUserMapper.INSTANCE.toUser(userRequestDTO);
	userRepository.save(user);
	return IUserMapper.INSTANCE.toUserResponseDTO(user);
  }

  public UserResponseDTO findById(ObjectId id) {
	User user = userRepository.findById(id).orElseThrow(() -> new UserException(ErrorType.USER_NOT_FOUND));
	return IUserMapper.INSTANCE.toUserResponseDTO(user);
  }

  public void deleteUser(ObjectId id) {
	userRepository.deleteById(id);
  }

  public List<UserResponseDTO> findAll() {
	List<User> users = userRepository.findAll();
	return IUserMapper.INSTANCE.toUserResponseDTOList(users);
  }

}
