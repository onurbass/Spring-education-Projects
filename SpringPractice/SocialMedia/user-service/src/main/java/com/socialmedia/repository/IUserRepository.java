package com.socialmedia.repository;

import com.socialmedia.repository.entity.UserProfile;
import com.socialmedia.repository.enums.EStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends MongoRepository<UserProfile, String> {

  Boolean existsByUsername(String username);

  Optional<UserProfile> findByAuthId(Long authId);

  Optional<UserProfile> findByUsername(String username);

  List<UserProfile> findUserProfileByStatus(EStatus eStatus);

  List<UserProfile> findUserProfileByStatus(String string);

}
