package com.seyithandilek.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seyithandilek.exception.MovieNotFoundException;
import com.seyithandilek.model.Movie;
import com.seyithandilek.repository.MovieRepository;


@Service
@Transactional
public class MovieServiceImp implements MovieService {
	
	private final MovieRepository movieRepository;

	@Autowired
	public MovieServiceImp(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public Movie addMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public Movie updateMovie(Long id, Movie movie) {
		Movie updateMovie=findMovieById(id);
		
		updateMovie.setName(movie.getName());
		updateMovie.setDirectoryName(movie.getName());
		updateMovie.setCategory(movie.getCategory());
		updateMovie.setImageUrl(movie.getImageUrl());
		updateMovie.setPublishDate(movie.getPublishDate());
		
		return movieRepository.save(updateMovie);
	}

	@Override
	public List<Movie> findAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Movie findMovieById(Long id) {
		return movieRepository.findMovieById(id)
				.orElseThrow(()-> new MovieNotFoundException("Movie by id:"+id+" was not found"));
	}

	@Override
	public void deleteMovie(Long id) {
		movieRepository.deleteMovieById(id);
	}

}
