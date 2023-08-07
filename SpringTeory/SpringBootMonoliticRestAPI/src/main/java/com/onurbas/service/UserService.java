package com.onurbas.service;

import com.onurbas.exception.BadRequestException;
import com.onurbas.exception.InternalServerErrorException;
import com.onurbas.exception.ResourceNotFoundException;
import com.onurbas.model.User;
import com.onurbas.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final IUserRepository userRepository;

  public List<User> findAll() {
	try {
	  return userRepository.findAll();
	} catch (Exception e) {
	  throw new InternalServerErrorException("An error occurred while fetching categories");
	}
  }

  public User findById(Long id) {
	if (id <= 0) {
	  throw new BadRequestException("Invalid user ID: " + id);
	}

	Optional<User> userOptional = userRepository.findById(id);
	if (userOptional.isEmpty()) {
	  throw new ResourceNotFoundException("User not found with ID: " + id);
	}

	return userOptional.get();
  }

  public User save(User user) {
	try {
	  return userRepository.save(user);
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
