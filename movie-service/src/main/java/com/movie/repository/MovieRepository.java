package com.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

}
