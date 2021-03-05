package com.seyithandilek.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.seyithandilek.model.Movie;

@Service
@Transactional
public interface MovieService {

	Movie addMovie(Movie movie);

	Movie updateMovie(Long id, Movie movie);

	List<Movie> findAllMovies();

	Movie findMovieById(Long id);

	void deleteMovie(Long id);

}
