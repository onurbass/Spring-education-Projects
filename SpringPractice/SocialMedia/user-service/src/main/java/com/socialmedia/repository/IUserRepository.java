package com.socialmedia.repository;

import com.socialmedia.repository.entity.UserProfile;
import com.socialmedia.repository.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserProfile, Long> {

  Boolean existsByUsername(String username);

  Optional<UserProfile> findByAuthId(Long authId);

  Optional<UserProfile> findByUsername(String username);

  List<UserProfile> findUserProfileByStatus(EStatus eStatus);
  List<UserProfile> findUserProfileByStatus(String string);


}
