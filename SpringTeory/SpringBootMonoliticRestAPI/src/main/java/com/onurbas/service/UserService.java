package com.onurbas.service;

import com.onurbas.dto.response.UserResponseDTO;
import com.onurbas.dto.request.UserRequestDTO;
import com.onurbas.exception.BadRequestException;
import com.onurbas.exception.InternalServerErrorException;
import com.onurbas.exception.ResourceNotFoundException;
import com.onurbas.mapper.IUserMapper;
import com.onurbas.model.User;
import com.onurbas.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final IUserRepository userRepository;

  public List<UserResponseDTO> findAll() {
	try {
	  return IUserMapper.INSTANCE.userListToUserDTOList(userRepository.findAll());
	} catch (DataAccessException e) {
	  throw new InternalServerErrorException("An error occurred while fetching categories");
	}
  }

  public UserResponseDTO findById(Long id) {
	if (id <= 0) {
	  throw new BadRequestException("Invalid user ID: " + id);
	}

	Optional<User> userOptional = userRepository.findById(id);
	if (userOptional.isEmpty()) {
	  throw new ResourceNotFoundException("User not found with ID: " + id);
	}
	UserResponseDTO userResponseDTO = IUserMapper.INSTANCE.userToUserDto(userRepository.findById(id).get());
	return userResponseDTO;
  }
  public User getById(Long id) {
	if (id <= 0) {
	  throw new BadRequestException("Invalid user ID: " + id);
	}

	Optional<User> userOptional = userRepository.findById(id);
	if (userOptional.isEmpty()) {
	  throw new ResourceNotFoundException("User not found with ID: " + id);
	}

	return userOptional.get();
  }
  public UserResponseDTO save(UserRequestDTO userRequestDTO) {
	try {
	  if (userRequestDTO == null) {
		throw new BadRequestException("User cannot be null");
	  }
	  User savedUser = userRepository.save(IUserMapper.INSTANCE.userRequestDTOToUser(userRequestDTO));
	  UserResponseDTO savedUserToUserResponseDTO = IUserMapper.INSTANCE.userToUserDto(savedUser);
	  return savedUserToUserResponseDTO;
	} catch (Exception e) {
	  throw new InternalServerErrorException("An error occurred while saving user");
	}
  }
public UserResponseDTO update(UserRequestDTO userRequestDTO,Long id){
  userRequestDTO.setId(id);
	User user = IUserMapper.INSTANCE.userRequestDTOToUser(userRequestDTO);
	User updatedUser = userRepository.save(user);
	return IUserMapper.INSTANCE.userToUserDto(updatedUser);
}
  public void deleteById(Long id) {
	Optional<User> user = userRepository.findById(id);
	try {
	  if (user.isEmpty()) {
		throw new ResourceNotFoundException("User not found with ID: " + id);
	  }
	  userRepository.deleteById(id);
	} catch (Exception e) {
	  throw new InternalServerErrorException("An error occurred while deleting user");
	}
  }

}
