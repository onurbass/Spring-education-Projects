package com.socialmedia.service;

import com.socialmedia.dto.request.UserProfileUpdateRequestDto;
import com.socialmedia.dto.request.UserSaveRequestDto;
import com.socialmedia.exception.ErrorType;
import com.socialmedia.exception.UserManagerException;
import com.socialmedia.manager.IAuthManager;
import com.socialmedia.mapper.IUserMapper;
import com.socialmedia.rabbitmq.model.RegisterModel;
import com.socialmedia.repository.IUserRepository;
import com.socialmedia.repository.entity.UserProfile;
import com.socialmedia.repository.enums.EStatus;
import com.socialmedia.utility.JwtTokenManager;
import com.socialmedia.utility.ServiceManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/*
    update metodu yazalım
    update metodu userprofile bilgilerini güncelleyecek aynı zamanda...
    update metodunda username veya email in ikisinden biri değişmiş ise
    authServisde de güncelleme yapsın..
 */

@Service
public class UserService extends ServiceManager<UserProfile, Long> {

  //Dependency Injec -->> constructor injection, setter injection, field injection
  private final IUserRepository userRepository;
  private final JwtTokenManager jwtTokenManager; //singleton üretilen JwtTokenManager sınıfının bu AuthService'e çağırılıp kullanıma açılması işlemidir..
  private final IAuthManager authManager;
  private final CacheManager cacheManager;

  public UserService(IUserRepository userRepository,JwtTokenManager jwtTokenManager,IAuthManager authManager,CacheManager cacheManager) {
	super(userRepository);
	this.userRepository = userRepository;
	this.jwtTokenManager = jwtTokenManager;
	this.authManager = authManager;
	this.cacheManager = cacheManager;
  }

  public Boolean createNewUser(UserSaveRequestDto dto) {
	try {
	  UserProfile userProfile = IUserMapper.INSTANCE.toUserProfile(dto);

	  save(userProfile);
	  return true;
	} catch (Exception e) {
	  throw new UserManagerException(ErrorType.USER_NOT_CREATED);
	}
  }

  public String activateStatus(String token) {

	Optional<Long> authId = jwtTokenManager.getAuthIdFromToken(token);

	if (authId.isEmpty()) {
	  throw new UserManagerException(ErrorType.INVALID_TOKEN);
	}

	Optional<UserProfile> userProfile = userRepository.findByAuthId(authId.get());
	if (userProfile.isEmpty()) {
	  throw new UserManagerException(ErrorType.USER_NOT_FOUND);
	}
	userProfile.get().setStatus(EStatus.ACTIVE);
	update(userProfile.get());
	return "Hesabınız aktive olmustur.";
  }

  @Transactional
  public String updateUserProfile(UserProfileUpdateRequestDto dto) {
	Optional<Long> authId = jwtTokenManager.getAuthIdFromToken(dto.getToken());
	if (authId.isEmpty()) {
	  throw new UserManagerException(ErrorType.INVALID_TOKEN);
	}
	Optional<UserProfile> userProfile = userRepository.findByAuthId(authId.get());
	if (userProfile.isEmpty()) {
	  throw new UserManagerException(ErrorType.USER_NOT_FOUND);
	}

	if (!userProfile.get().getEmail().equals(dto.getEmail()) ||
			!userProfile.get().getUsername().equals(dto.getUsername())) {
	  userProfile.get().setEmail(dto.getEmail());
	  userProfile.get().setUsername(dto.getUsername());

	  authManager.updateAuth(IUserMapper.INSTANCE.toAuthUpdateRequestDto(userProfile.get()));
	}

//        userProfile = Optional.of(IUserMapper.INSTANCE.toUserProfile(dto));  // id ve authId null geliyor..

	userProfile.get().setAbout(dto.getAbout());
	userProfile.get().setPhone(dto.getPhone());
	userProfile.get().setAddress(dto.getAddress());
	userProfile.get().setName(dto.getName());
	userProfile.get().setSurName(dto.getSurName());
	userProfile.get().setSurName(dto.getSurName());
	userProfile.get().setAvatar(dto.getAvatar());

	update(userProfile.get());
	//cacheManager.getCache("find_by_username").evict(userProfile.get().getUsername());//cache silme
	cacheManager.getCache("find_by_username").put(userProfile.get().getUsername(),userProfile.get());//cache güncelleme
	return "Update Başarılı";

  }

  public Boolean createNewUserWithRabbitmq(RegisterModel model) {
	try {
	  UserProfile userProfile = IUserMapper.INSTANCE.toUserProfile(model);

	  save(userProfile);
	  return true;
	} catch (Exception e) {
	  throw new UserManagerException(ErrorType.USER_NOT_CREATED);
	}
  }

  @Cacheable(value = "find_by_username")
  public UserProfile findByUsername(String username) {
	try {
	  Thread.sleep(1000L);
	} catch (InterruptedException e) {
	  throw new RuntimeException(e);
	}
	return userRepository.findByUsername(username)
						 .orElseThrow(() -> new UserManagerException(ErrorType.USER_NOT_FOUND));
  }

  @Cacheable(value = "find_by_status2")
  public List<UserProfile> findByStatus(EStatus eStatus) {
	try {
	  Thread.sleep(1000);
	} catch (InterruptedException e) {
	  throw new RuntimeException(e);
	}
	return userRepository.findUserProfileByStatus(eStatus);
  }

  @Cacheable(value = "find_by_status", key = "#eStatus.toString().toUpperCase(T(java.util.Locale).ENGLISH)")
  public List<UserProfile> findByStatus(String eStatus) {

	try {
	  Thread.sleep(1000);
	} catch (InterruptedException e) {
	  throw new RuntimeException(e);
	}
	EStatus status;
	try {
	  status = EStatus.valueOf(eStatus.toUpperCase(Locale.ENGLISH));
	} catch (IllegalArgumentException e) {
	  throw new UserManagerException(ErrorType.STATUS_NOT_FOUND);
	}
	return userRepository.findUserProfileByStatus(status);
  }

  public void deleteUser(Long id) {
	Optional<UserProfile> userProfile = userRepository.findByAuthId(id);

	if (userProfile.isEmpty()) {
	  throw new UserManagerException(ErrorType.USER_NOT_FOUND);
  }
	userProfile.get().setStatus(EStatus.DELETED);
	update(userProfile.get());
	cacheManager.getCache("find_by_username").evict(userProfile.get().getUsername());//cache silme
  }



  }

