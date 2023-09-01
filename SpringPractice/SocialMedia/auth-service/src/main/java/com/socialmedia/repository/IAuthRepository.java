package com.socialmedia.repository;

import com.socialmedia.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface IAuthRepository extends JpaRepository<Auth, Long> {

  /*@Query(value = "Select a FROM  Auth as a where a.username = username and password = password")*/
 Optional<Auth> findOptionalByUsernameAndPassword(String username, String password);

  Boolean existsByUsername (String username);


}
