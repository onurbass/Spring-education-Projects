package com.example.controller;

import com.example.constant.Endpoints;
import com.example.model.Moment;
import com.example.service.MomentService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1" + Endpoints.MOMENT)
@RequiredArgsConstructor
public class MomentController {

  private final MomentService momentService;

  @GetMapping("/")
  public ResponseEntity<List<Moment>> getMoments() {

	return ResponseEntity.ok(momentService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Moment> getMomentById(@PathVariable ObjectId id) {

	return ResponseEntity.ok(momentService.findById(id));
  }

  @PostMapping("/")
  public ResponseEntity<Moment> saveMoment(@RequestBody Moment moment) {

	return ResponseEntity.created(URI.create("/")).body(momentService.saveMoment(moment));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMoment(@PathVariable ObjectId id) {
	momentService.deleteMoment(id);
	return ResponseEntity.noContent().build();
  }

  @GetMapping("/search/{key}")
  public ResponseEntity<List<Moment>> searchTimeline(@PathVariable String key) {
	return ResponseEntity.ok(momentService.searchTimeline(key));
  }
}
