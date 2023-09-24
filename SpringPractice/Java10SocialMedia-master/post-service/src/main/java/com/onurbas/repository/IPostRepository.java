package com.onurbas.repository;


import com.onurbas.repository.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPostRepository extends MongoRepository<Post, String> {


}
