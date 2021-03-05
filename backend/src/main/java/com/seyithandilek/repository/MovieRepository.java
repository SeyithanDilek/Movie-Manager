package com.seyithandilek.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seyithandilek.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	void deleteMovieById(Long id);
	
	Optional<Movie> findMovieById(Long id);

}
