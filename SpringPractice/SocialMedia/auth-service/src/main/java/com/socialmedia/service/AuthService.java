package com.socialmedia.service;


import com.socialmedia.dto.request.ActivationRequestDto;
import com.socialmedia.dto.request.LoginRequestDto;
import com.socialmedia.dto.request.RegisterRequestDto;
import com.socialmedia.dto.response.RegisterResponseDto;
import com.socialmedia.mapper.IAuthMapper;
import com.socialmedia.repository.IAuthRepository;
import com.socialmedia.repository.entity.Auth;
import com.socialmedia.repository.enums.EStatus;
import com.socialmedia.utility.CodeGenerator;
import com.socialmedia.utility.ServiceManager;
import org.springframework.stereotype.Service;
import java.util.Optional;

/*
1- Register islemi yapıcaz
dto alsın dto dönsün
request dto--> username, email, password
response dto--> username, id, activationCode
repodan controllara kadar yazalım

2 - Login methodu yazalım
dto alsın eğer veritabanında kayıt varsa true dönsün yoksa false dönsün


 */
@Service
public class AuthService extends ServiceManager<Auth, Long> {

  private final IAuthRepository authRepository;

  public AuthService(IAuthRepository authRepository) {
	super(authRepository);
	this.authRepository = authRepository;
  }

  public RegisterResponseDto register(RegisterRequestDto dto) {

	//max 5 parametreden sonra kesinlikle dto kullanın
	Auth auth = IAuthMapper.INSTANCE.toAuth(dto);


	auth.setActivationCode(CodeGenerator.generatedCode());
	save(auth);
	RegisterResponseDto responseDto = IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
	return responseDto;

  }

  public Boolean login(LoginRequestDto dto) {
	Optional<Auth> optionalAuth = authRepository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());

	if (optionalAuth.isEmpty()) {
	  throw new RuntimeException("Böyle biri veritabanında yok");
	} else {
	  return true;

	}

  }

  public Boolean isActivationMatch(ActivationRequestDto dto) {
	Optional<Auth> auth = findById(dto.getId());

	if (auth.isEmpty()) {
	  throw new RuntimeException("Kullanıcı bulunamadı");
	}
	if (auth.get().getActivationCode().equals(dto.getActivationCode())) {
	  auth.get().setStatus(EStatus.ACTIVE);
	  update(auth.get());
	  return true;
	} else {
	  throw new RuntimeException("Kod hatalı");
	}

  }

}



