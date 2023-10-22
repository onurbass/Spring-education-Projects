package com.example.service;

import com.example.dto.UserResponseDTO;
import com.example.exception.ErrorType;
import com.example.exception.TimelineException;
import com.example.exception.UserException;
import com.example.model.Moment;
import com.example.model.Timeline;
import com.example.repository.ITimelineRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimelineService {

  private final ITimelineRepository timelineRepository;
  private final UserService userService;
  private final MomentService momentService;

  public Timeline saveTimeline(Timeline timeline) {
	UserResponseDTO userResponseDTO = userService.findById(new ObjectId(timeline.getUserId()));

	if (userResponseDTO == null) {
	  throw new UserException(ErrorType.USER_NOT_FOUND);
	}
	List<Moment> moments = timeline.getMoments();
	for (Moment moment : moments) {
	  momentService.saveMoment(moment);
	}
	return timelineRepository.save(timeline);
  }

  public Timeline findById(ObjectId id) {

	return timelineRepository.findById(id).orElseThrow(() -> new TimelineException(ErrorType.TIMELINE_NOT_FOUND));
  }

  public void deleteTimeline(ObjectId id) {
	List<Moment> moments = findMomentsByTimeline(id.toString());
	for (Moment moment : moments) {
	  momentService.deleteMoment(moment.getId());
	}

	timelineRepository.deleteById(id);
  }

  public List<Timeline> findAll() {
	return timelineRepository.findAll();
  }

  public List<Moment> findMomentsByTimeline(String timelineId) {
	Timeline timeline = findById(new ObjectId(timelineId));
	return timeline.getMoments();
  }

  public List<Timeline> searchTimeline(String key) {
	return timelineRepository.findTimelinesByTitleOrDescriptionContaining(key);
  }
}
