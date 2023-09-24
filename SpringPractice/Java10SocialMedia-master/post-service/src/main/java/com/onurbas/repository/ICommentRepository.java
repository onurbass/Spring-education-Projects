package com.onurbas.repository;

import com.onurbas.repository.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICommentRepository extends MongoRepository<Comment, String> {


}
