package com.seyithandilek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seyithandilek.model.Movie;
import com.seyithandilek.service.MovieServiceImp;

@Controller
@RequestMapping("/movie")
public class MovieController {

	private final MovieServiceImp service;

	@Autowired
	public MovieController(MovieServiceImp service) {
		this.service = service;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Movie>> getAllMovies() {
		List<Movie> movies = service.findAllMovies();
		return ResponseEntity.ok(movies);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
		Movie movie = service.findMovieById(id);
		return ResponseEntity.ok(movie);
	}

	@PostMapping("/add")
	public ResponseEntity<Movie> addEmployee(@RequestBody Movie movie) {
		Movie newMovie = service.addMovie(movie);
		return ResponseEntity.ok(newMovie);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long id,@RequestBody Movie movie) {
		Movie updateMovie = service.updateMovie(id, movie);
		return ResponseEntity.ok(updateMovie);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteMoving(@PathVariable("id") Long id) {
		service.deleteMovie(id);
		return ResponseEntity.ok(null);
	}

}
