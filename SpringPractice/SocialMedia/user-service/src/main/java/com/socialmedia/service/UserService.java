package com.socialmedia.service;

import com.socialmedia.dto.UserSaveRequestDto;
import com.socialmedia.dto.UserUpdateRequestDto;
import com.socialmedia.exception.ErrorType;
import com.socialmedia.exception.UserManagerException;
import com.socialmedia.manager.IAuthManager;
import com.socialmedia.mapper.IUserMapper;
import com.socialmedia.repository.IUserRepository;
import com.socialmedia.repository.entity.UserProfile;

import com.socialmedia.repository.enums.EStatus;
import com.socialmedia.utility.JWTTokenManager;
import com.socialmedia.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends ServiceManager<UserProfile, Long> {

  private final IUserRepository userRepository;
  private final JWTTokenManager jwtTokenManager;
  private final IAuthManager authManager;

  public UserService(IUserRepository userRepository,JWTTokenManager jwtTokenManager,IAuthManager authManager) {
	super(userRepository);
	this.userRepository = userRepository;
	this.jwtTokenManager = jwtTokenManager;
	this.authManager = authManager;
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

  public String update(UserUpdateRequestDto dto) {
	Optional<Long> authId = jwtTokenManager.getAuthIdFromToken(dto.getToken());
	if (authId.isEmpty()) {
	  throw new UserManagerException(ErrorType.INVALID_TOKEN);
	}
	Optional<UserProfile> userProfile = userRepository.findUserProfileByAuthId(authId.get());

	if (userProfile.isEmpty()) {
	  throw new UserManagerException(ErrorType.USER_NOT_FOUND);
	}

	if (!userProfile.get().getEmail().equals(dto.getEmail())
			|| !userProfile.get().getUsername().equals(dto.getUsername())) {
	  userProfile.get().setEmail(dto.getEmail());
	  userProfile.get().setUsername(dto.getUsername());
	  authManager.updateAuth(IUserMapper.INSTANCE.toUpdateRequestDto(userProfile.get()));
	}
	userProfile.get().setAvatar(dto.getAvatar());
	userProfile.get().setAbout(dto.getAbout());
	userProfile.get().setPhone(dto.getPhone());
	userProfile.get().setAddress(dto.getAddress());
	update(userProfile.get());
	return "Guncelleme başarılı";


  }

  public String activateStatus(String token) {
	Optional<Long> authId = jwtTokenManager.getAuthIdFromToken(token);
	if (authId.isEmpty()) {
	  throw new UserManagerException(ErrorType.INVALID_TOKEN);
	}
	Optional<UserProfile> userProfile = userRepository.findUserProfileByAuthId(authId.get());

	if (userProfile.isEmpty()) {
	  throw new UserManagerException(ErrorType.USER_NOT_FOUND);
	}
	userProfile.get().setStatus(EStatus.ACTIVE);
	update(userProfile.get());
	return "Kullanıcı aktif edildi";
  }

}


