package com.onurbas.repository;

import com.onurbas.repository.entity.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ILikeRepository extends MongoRepository<Like, String> {


}
