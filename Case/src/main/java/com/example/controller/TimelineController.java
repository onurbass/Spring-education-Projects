package com.example.controller;

import com.example.constant.Endpoints;
import com.example.model.Moment;
import com.example.model.Timeline;
import com.example.service.TimelineService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1" + Endpoints.TIMELINE)
@RequiredArgsConstructor
public class TimelineController {

  private final TimelineService timelineService;

  @GetMapping("/")
  public ResponseEntity<List<Timeline>> getTimelines() {

	return ResponseEntity.ok(timelineService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Timeline> getTimelineById(@PathVariable ObjectId id) {

	return ResponseEntity.ok(timelineService.findById(id));
  }

  @PostMapping("/")
  public ResponseEntity<Timeline> saveTimeline(@RequestBody Timeline timeline) {

	return ResponseEntity.created(URI.create("/")).body(timelineService.saveTimeline(timeline));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTimeline(@PathVariable ObjectId id) {
	timelineService.deleteTimeline(id);
	return ResponseEntity.noContent().build();
  }

  @GetMapping(Endpoints.MOMENT + "{timelineId}")
  public ResponseEntity<List<Moment>> findMomentsByTimeline(@PathVariable String timelineId) {

	return ResponseEntity.ok(timelineService.findMomentsByTimeline(timelineId));
  }

  @GetMapping("/search/{key}")
  public ResponseEntity<List<Timeline>> searchTimeline(@PathVariable String key) {
	return ResponseEntity.ok(timelineService.searchTimeline(key));
  }
}
