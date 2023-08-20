package com.onurbas.service;

import com.onurbas.constant.EndPoints;
import com.onurbas.dto.request.RegisterRequestDTO;
import com.onurbas.repository.IAuthRepository;
import com.onurbas.repository.entity.Auth;
import com.onurbas.utility.ServiceManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

  private final IAuthRepository repository;

  public AuthService(IAuthRepository repository) {
	super(repository);
	this.repository = repository;
  }

  public  String doLogin(RegisterRequestDTO dto){
	return null;
  }
}
