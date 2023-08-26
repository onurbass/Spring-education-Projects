package com.socialmedia.repository;

import com.socialmedia.repository.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserRepository extends JpaRepository <UserProfile, Long> {

    Optional<UserProfile> existsByUsername (String username);
    Optional<UserProfile> findUserProfileByAuthId (Long id);

}
