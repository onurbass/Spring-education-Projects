package com.onurbas.service;

import com.onurbas.model.User;
import com.onurbas.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final IUserRepository userRepository;

  public List<User> findAll(){
	return userRepository.findAll();
  }
  public Optional<User> findById(Long id){
	return userRepository.findById(id);
  }
  public User save(User user){
	return userRepository.save(user);
  }
  public void deleteById(Long id){
	 userRepository.deleteById(id);
  }



}
