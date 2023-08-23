package com.socialmedia.service;

import com.socialmedia.dto.request.ActivationRequestDto;
import com.socialmedia.dto.request.LoginRequestDto;
import com.socialmedia.dto.request.RegisterRequestDto;
import com.socialmedia.dto.response.RegisterResponseDto;
import com.socialmedia.exception.AuthManagerException;
import com.socialmedia.exception.ErrorType;
import com.socialmedia.mapper.IAuthMapper;
import com.socialmedia.repository.IAuthRepository;
import com.socialmedia.repository.entity.Auth;
import com.socialmedia.repository.enums.EStatus;
import com.socialmedia.utility.CodeGenerator;
import com.socialmedia.utility.JWTTokenManager;
import com.socialmedia.utility.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/*
    1-
    Register işlemi yapcaz
    dto alsın dto dönsün
    request dto --> username, email, password
    response dto --> username, id, activationCode
    repodan controllera kadar yazalım


    GetMapping --> veriler nerden geliyor url
    PostMapping --> Body

    2- Login methodu yazalım
        dto alsın eğer veritabnında kayıt varsa true dönsün yoksa false dönsün

    3- Active status --> Boolean dönsün

    */

@Service
public class AuthService extends ServiceManager<Auth, Long> {

  private final IAuthRepository authRepository;
  private final JWTTokenManager jwtTokenManager;


  public AuthService(IAuthRepository authRepository,JWTTokenManager jwtTokenManager) {
	super(authRepository);
	this.authRepository = authRepository;
	this.jwtTokenManager = jwtTokenManager;
  }

  public RegisterResponseDto register(RegisterRequestDto dto) {

	Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
	auth.setActivationCode(CodeGenerator.generateCode());
	try {
	  save(auth);
	} catch (Exception e) {
	  throw new RuntimeException("Kayıt başarısız!!!!");
	}

	RegisterResponseDto responseDto = IAuthMapper.INSTANCE.toRegisterResponseDto(auth);

	return responseDto;
  }

  public String login(LoginRequestDto dto) {
	Optional<Auth> optionalAuth = authRepository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
	if (optionalAuth.isEmpty()) {
	  throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
	}
	if (!optionalAuth.get().getStatus().equals(EStatus.ACTIVE)) {
	  throw new AuthManagerException(ErrorType.ACCOUNT_NOT_ACTIVE);
	}

	return jwtTokenManager.createToken(optionalAuth.get().getId())
						  .orElseThrow(() -> {
							throw new AuthManagerException(ErrorType.INVALID_TOKEN);
						  });

  }

  public String activateStatus(ActivationRequestDto dto) {

	Optional<Auth> optionalAuth = findById(jwtTokenManager.getIdFromToken(dto.getToken()).orElseThrow(() -> {
	  throw new AuthManagerException(ErrorType.INVALID_TOKEN);
	}));


	if (optionalAuth.isEmpty()) {
	  throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
	}
	if (optionalAuth.get().getStatus().equals(EStatus.ACTIVE)) {
	  throw new AuthManagerException(ErrorType.ALREADY_ACTIVE);
	}
	if (dto.getActivationCode().equals(optionalAuth.get().getActivationCode())) {
	  optionalAuth.get().setStatus(EStatus.ACTIVE);
	  update(optionalAuth.get());
	  return "Hesabınız aktif edilmiştir";
	} else {
	  throw new AuthManagerException(ErrorType.INVALID_CODE);
	}
  }
}


