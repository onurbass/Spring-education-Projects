package com.onurbas.repository;

import com.onurbas.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IPostRepository extends JpaRepository<Post, Long> {

  List<Post> findPostsByUserId(Long id);

  List<Post> findPostsByCategoryId(Long id);

}
