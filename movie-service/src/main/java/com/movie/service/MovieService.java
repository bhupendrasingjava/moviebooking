package com.movie.service;

import java.util.List;
import java.util.Optional;

import com.movie.entity.MovieEntity;

public interface MovieService {
	
	MovieEntity saveMovie(MovieEntity movie);
	List<MovieEntity> movieList();
	Optional<MovieEntity> getMovieById(long id);
	String deleteMovie(long id);
	MovieEntity updateMovie(MovieEntity movie);
}
