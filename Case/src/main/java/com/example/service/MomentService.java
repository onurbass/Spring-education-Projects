package com.example.service;

import com.example.exception.ErrorMessage;
import com.example.exception.ErrorType;
import com.example.exception.MomentException;
import com.example.model.Moment;
import com.example.model.Timeline;
import com.example.repository.IMomentRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MomentService {

  private final IMomentRepository momentRepository;

  public Moment saveMoment(Moment moment) {
	if (moment == null) throw new MomentException(ErrorType.BAD_REQUEST);
	return momentRepository.save(moment);
  }

  public Moment findById(ObjectId id) {
	return momentRepository.findById(id).orElseThrow(() -> new MomentException(ErrorType.MOMENT_NOT_FOUND));

  }

  public void deleteMoment(ObjectId id) {
	momentRepository.deleteById(id);
  }

  public List<Moment> findAll() {

	return momentRepository.findAll();
  }

  public List<Moment> searchTimeline(String key) {
	return momentRepository.findMomentsByByTitleOrDescriptionContaining(key);
  }
}
