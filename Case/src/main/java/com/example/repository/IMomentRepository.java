package com.example.repository;

import com.example.model.Moment;

import com.example.model.Timeline;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMomentRepository extends MongoRepository<Moment, ObjectId> {

  @Query("{ $or: [ { 'info.title': { $regex: ?0, $options: 'i' } }, { 'info.description': { $regex: ?0, $options: 'i' } } ] }")
  List<Moment> findMomentsByByTitleOrDescriptionContaining(String keyword);

}
