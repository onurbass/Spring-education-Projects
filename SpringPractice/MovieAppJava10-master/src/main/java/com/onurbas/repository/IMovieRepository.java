package com.onurbas.repository;

import com.onurbas.repository.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
public interface IMovieRepository extends JpaRepository<Movie,Long> {


    List<Movie> findAllByPremiredBefore(LocalDate date);
}
