package com.example.repository;

import com.example.model.Timeline;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITimelineRepository extends MongoRepository<Timeline, ObjectId> {

  @Query("{ $or: [ { 'info.title': { $regex: ?0, $options: 'i' } }, { 'info.description': { $regex: ?0, $options: 'i' } } ] }")
  List<Timeline> findTimelinesByTitleOrDescriptionContaining(String keyword);

}
