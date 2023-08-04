package com.onurbas.repository;

import com.onurbas.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository  extends JpaRepository<User,Long> {


  Optional<User> findByEmailAndAndPassword(String email,String password);
  List<User> findAllByOrderByName();

}
