package com.onurbas.service;

import com.onurbas.repository.IAuthRepository;
import com.onurbas.repository.entity.Auth;
import com.onurbas.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

  private final IAuthRepository repository;

  public AuthService(IAuthRepository repository) {
	super(repository);
	this.repository = repository;
  }
}
