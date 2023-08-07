package com.onurbas.service;

import com.onurbas.dto.response.UserDTO;
import com.onurbas.dto.response.UserDTO;
import com.onurbas.dto.response.UserDTO;
import com.onurbas.exception.BadRequestException;
import com.onurbas.exception.InternalServerErrorException;
import com.onurbas.exception.ResourceNotFoundException;
import com.onurbas.mapper.IUserMapper;
import com.onurbas.mapper.IUserMapper;
import com.onurbas.mapper.IUserMapper;
import com.onurbas.model.User;
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

  public List<UserDTO> findAll() {
	try {
	  return IUserMapper.INSTANCE.userListToUserDTOList(userRepository.findAll());
	} catch (DataAccessException e) {
	  throw new InternalServerErrorException("An error occurred while fetching categories");
	}
  }

  public UserDTO findById(Long id) {
	if (id <= 0) {
	  throw new BadRequestException("Invalid user ID: " + id);
	}

	Optional<User> userOptional = userRepository.findById(id);
	if (userOptional.isEmpty()) {
	  throw new ResourceNotFoundException("User not found with ID: " + id);
	}
	UserDTO userDTO = IUserMapper.INSTANCE.userToUserDto(userRepository.findById(id).get());
	return userDTO;
  }

  public UserDTO save(User user) {
	try {
	  if (user == null) {
		throw new BadRequestException("User cannot be null");
	  }
	  UserDTO userDto = IUserMapper.INSTANCE.userToUserDto(userRepository.save(user));
	  return userDto;
	} catch (Exception e) {
	  throw new InternalServerErrorException("An error occurred while saving user");
	}
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
