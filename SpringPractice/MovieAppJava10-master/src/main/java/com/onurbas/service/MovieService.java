package com.onurbas.service;

import com.onurbas.repository.IMovieRepository;
import com.onurbas.repository.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

  private final IMovieRepository movieRepository;

  public List<Movie> findAllByPremiredBefore(String date) {

	LocalDate myDate = null;
	try {
	  myDate = LocalDate.parse(date);
	} catch (Exception e) {
	  throw new RuntimeException(e.toString());
	}

	return movieRepository.findAllByPremiredBefore(myDate);
  }
}
