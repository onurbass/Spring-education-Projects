package com.socialmedia.service;

import com.socialmedia.dto.request.ActivateRequestDto;
import com.socialmedia.dto.request.AuthUpdateRequestDto;
import com.socialmedia.dto.request.LoginRequestDto;
import com.socialmedia.dto.request.RegisterRequestDto;
import com.socialmedia.dto.response.RegisterResponseDto;
import com.socialmedia.exception.AuthManagerException;
import com.socialmedia.exception.ErrorType;
import com.socialmedia.manager.IUserManager;
import com.socialmedia.mapper.IAuthMapper;
import com.socialmedia.rabbitmq.model.MailModel;
import com.socialmedia.rabbitmq.producer.ActivationProducer;
import com.socialmedia.rabbitmq.producer.MailProducer;
import com.socialmedia.rabbitmq.producer.RegisterProducer;
import com.socialmedia.repository.IAuthRepository;
import com.socialmedia.repository.entity.Auth;
import com.socialmedia.repository.enums.EStatus;
import com.socialmedia.utility.CodeGenerator;
import com.socialmedia.utility.JwtTokenManager;
import com.socialmedia.utility.ServiceManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
//@Transactional BÜTÜN Metodları kapsıyor.. buraya yazarsak..
public class AuthService extends ServiceManager<Auth, Long> {

  //Dependency Injec -->> constructor injection, setter injection, field injection
  private final IAuthRepository authRepository;
  private final JwtTokenManager jwtTokenManager; //singleton üretilen JwtTokenManager sınıfının bu AuthService'e çağırılıp kullanıma açılması işlemidir..
  private IUserManager userManager;
  private final RegisterProducer registerProducer;

  private final ActivationProducer activationProducer;
  private final MailProducer mailProducer;

  public AuthService(IAuthRepository authRepository,JwtTokenManager jwtTokenManager,IUserManager userManager,RegisterProducer registerProducer,ActivationProducer activationProducer,MailProducer mailProducer) {
	super(authRepository);
	this.authRepository = authRepository;
	this.jwtTokenManager = jwtTokenManager;
	this.userManager = userManager;
	this.registerProducer = registerProducer;
	this.activationProducer = activationProducer;
	this.mailProducer = mailProducer;
  }

  @Transactional
  public RegisterResponseDto register(RegisterRequestDto dto) {
	//Auth auth = Convertor.convertFromDtoToAuth(dto);
	Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
	auth.setActivationCode(CodeGenerator.generateCode());
	if (authRepository.existsByUsername(auth.getUsername())) {
	  throw new AuthManagerException(ErrorType.USERNAME_ALREADY_EXIST);
	}
	save(auth);
	userManager.save(IAuthMapper.INSTANCE.toUserSaveRequestDto(auth));

	RegisterResponseDto responseDto = IAuthMapper.INSTANCE.toRegisterRequestDto(auth);
	String token = jwtTokenManager.createToken(auth.getId())
								  .orElseThrow(() -> new AuthManagerException(ErrorType.INVALID_TOKEN));

	responseDto.setToken(token);
	return responseDto;
  }

  @Transactional
  public RegisterResponseDto registerWithRabbitMq(RegisterRequestDto dto) {
	//Auth auth = Convertor.convertFromDtoToAuth(dto);
	Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
	auth.setActivationCode(CodeGenerator.generateCode());
	if (authRepository.existsByUsername(auth.getUsername())) {
	  throw new AuthManagerException(ErrorType.USERNAME_ALREADY_EXIST);
	}
	save(auth);
	//rabbitMq ile haberleştireceğiz
	registerProducer.sendNewUser(IAuthMapper.INSTANCE.toRegisterModel(auth));

	RegisterResponseDto responseDto = IAuthMapper.INSTANCE.toRegisterRequestDto(auth);
	String token = jwtTokenManager.createToken(auth.getId())
								  .orElseThrow(() -> new AuthManagerException(ErrorType.INVALID_TOKEN));

	//mail atma işlemi için mail service ile haberleşecek..
	MailModel mailModel = IAuthMapper.INSTANCE.toMailModel(auth);
	mailModel.setToken(token);//diğerleri ortak token olmadığı için setledik
	mailProducer.sendMail(mailModel);

	responseDto.setToken(token);
	return responseDto;
  }

  public String login(LoginRequestDto dto) {
	Optional<Auth> optionalAuth = authRepository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
	if (optionalAuth.isEmpty()) {
	  throw new AuthManagerException(ErrorType.LOGIN_ERROR);
	}
	if (!optionalAuth.get().getStatus().equals(EStatus.ACTIVE)) {
	  throw new AuthManagerException(ErrorType.ACCOUNT_NOT_ACTIVE);
	}
	//farklı bir optional kontrolü
	return jwtTokenManager.createToken(optionalAuth.get().getId(),optionalAuth.get().getRole())
						  .orElseThrow(() -> new AuthManagerException(ErrorType.TOKEN_NOT_CREATED));
  }

  @Transactional
  public String activateStatus(ActivateRequestDto dto) {

	Optional<Long> authId = jwtTokenManager.getIdFromToken(dto.getToken());
	if (authId.isEmpty()) {
	  throw new AuthManagerException(ErrorType.INVALID_TOKEN);
	}
	Optional<Auth> optionalAuth = findById(authId.get());

	if (optionalAuth.isEmpty()) {
	  throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
	}

	if (optionalAuth.get().getStatus().equals(EStatus.ACTIVE)) {
	  throw new AuthManagerException(ErrorType.ALREADY_ACTIVE);
	}

	if (dto.getActivationCode().equals(optionalAuth.get().getActivationCode())) {
	  optionalAuth.get().setStatus(EStatus.ACTIVE);
	  update(optionalAuth.get());

	  //userManager.activateStatus(dto.getToken());  //open feign ile haberleşme

	  activationProducer.activateStatus(dto.getToken()); //rabbitmq ile haberleşme

	  return "Hesabınız aktive edilmiştir.";
	} else {
	  throw new AuthManagerException(ErrorType.INVALID_CODE);
	}
  }

  public String updateAuth(AuthUpdateRequestDto dto) {
	Optional<Auth> optionalAuth = findById(dto.getId());
	if (optionalAuth.isEmpty()) {
	  throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
	}

	if (!optionalAuth.get().getUsername().equals(dto.getUsername()) && authRepository.existsByUsername(dto.getUsername())) {
	  throw new AuthManagerException(ErrorType.BAD_REQUEST);
	}
	optionalAuth.get().setUsername(dto.getUsername());
	optionalAuth.get().setEmail(dto.getEmail());
	update(optionalAuth.get());

	return "Update başarılı";

  }

  public void deleteAuth(Long id) {
	Optional<Auth> optionalAuth = findById(id);
	if (optionalAuth.isEmpty()) {
	  throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
	}
	if (optionalAuth.get().getStatus().equals(EStatus.DELETED)) {
	  throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
	}
	optionalAuth.get().setStatus(EStatus.DELETED);
	update(optionalAuth.get());
	userManager.deleteById(optionalAuth.get().getId());
  }

  }


