package com.theater.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theater.entity.TheaterEntity;

public interface TheaterRepository extends JpaRepository<TheaterEntity, Long> {
	
	

}
