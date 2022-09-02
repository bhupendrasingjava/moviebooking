package com.movie.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.movie.entity.MovieEntity;
import com.movie.repository.MovieRepository;

@RestController
@RequestMapping("/movie")
public class MovieController {

	private static final Logger log = LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	private MovieRepository repository;

	@PostMapping("/saveMovie")
	public MovieEntity saveMovie(@RequestBody MovieEntity movie) {
		MovieEntity saveMovie = repository.save(movie);
		return saveMovie;

	}

	@GetMapping("/getMovie")
	public List<MovieEntity> movieList() {
		List<MovieEntity> findAll = repository.findAll();
		return findAll;
	}

	@PutMapping("/updateMovie")
	public MovieEntity updateMovie(@RequestBody MovieEntity movie) {
		MovieEntity saveAndFlush = repository.saveAndFlush(movie);
		return saveAndFlush;
	}

	@DeleteMapping("/deleteMovie")
	public String deleteMovie(@RequestParam(value = "id", required = true) long id) {

		repository.deleteById(id);

		return "Success";
	}

	@GetMapping("/getMovieById")
	public MovieEntity getMovieById(@RequestParam(value = "id", required = true) long id) {
		log.info("Inside MovieController: getMovieById()");
		Optional<MovieEntity> movieEntity = repository.findById(id);
		log.debug("MovieController: getMovieById() calles successfully");
		return movieEntity.get();
	}


}
