package com.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.entity.MovieEntity;
import com.movie.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	 private MovieRepository movieRepository;

	@Override
	public MovieEntity saveMovie(MovieEntity movie) {
	
		return movieRepository.save(movie);
	}

	@Override
	public List<MovieEntity> movieList() {
		// TODO Auto-generated method stub
		return movieRepository.findAll();
	}

	@Override
	public Optional<MovieEntity> getMovieById(long id) {
		// TODO Auto-generated method stub
		Optional<MovieEntity> movieEntity = movieRepository.findById(id);
		return movieEntity;
	}

	@Override
	public String deleteMovie(long id) {
		// TODO Auto-generated method stub
		movieRepository.deleteById(id);
		return "Success";
	}

	@Override
	public MovieEntity updateMovie(MovieEntity movie) {
		
		MovieEntity saveAndFlush = movieRepository.save(movie);
		return saveAndFlush;
	}

   
}
