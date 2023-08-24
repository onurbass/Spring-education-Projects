package com.socialmedia.service;

import com.socialmedia.dto.UserSaveRequestDto;
import com.socialmedia.exception.ErrorType;
import com.socialmedia.exception.UserManagerException;
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

  public UserService(IUserRepository userRepository,JWTTokenManager jwtTokenManager) {
	super(userRepository);
	this.userRepository = userRepository;
	this.jwtTokenManager = jwtTokenManager;
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
	Optional<UserProfile> userProfile = userRepository.findUserProfileByAuthId(authId.get());

	if (userProfile.isEmpty()) {
	  throw new UserManagerException(ErrorType.USER_NOT_FOUND);
	}
	userProfile.get().setStatus(EStatus.ACTIVE);
	update(userProfile.get());
	return "Kullanıcı aktif edildi";
  }

}


