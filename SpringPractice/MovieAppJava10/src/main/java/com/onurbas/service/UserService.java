package com.onurbas.service;

import com.onurbas.repository.IUserRepository;
import com.onurbas.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final IUserRepository userRepository;


  public User createUser(User user){

   return userRepository.save(user);
 }

}
