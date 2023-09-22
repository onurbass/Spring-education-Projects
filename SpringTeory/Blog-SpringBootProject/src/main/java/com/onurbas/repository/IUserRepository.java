package com.onurbas.repository;

import com.onurbas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository //CRUD Create Read Update Delete
public interface IUserRepository extends JpaRepository<User, Long> {

  User findUserByEmail(String email);

}
