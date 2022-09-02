package com.theater.controller;

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

import com.theater.entity.TheaterEntity;
import com.theater.repository.TheaterRepository;

@RestController
@RequestMapping("/theater")
public class TheaterController {

	private static final Logger log = LoggerFactory.getLogger(TheaterController.class);
	@Autowired
	private TheaterRepository repository;

	@PostMapping("/saveTheater")
	public TheaterEntity Save(@RequestBody TheaterEntity theater) {
		TheaterEntity save = repository.save(theater);
		System.out.println(save);
		return save;
	}

	@GetMapping("/theaterList")
	public java.util.List<TheaterEntity> List() {
		java.util.List<TheaterEntity> findAll = repository.findAll();
		System.out.println(findAll);
		return findAll;
	}

	@PutMapping("/theaterUpdate")
	public TheaterEntity updateTheater(@RequestBody TheaterEntity theater) {
		TheaterEntity saveAndFlush = repository.saveAndFlush(theater);
		return saveAndFlush;
	}

	@DeleteMapping("/theaterDelete")
	public String deleteUser(@RequestParam(value = "id", required = true) long id) {
		repository.deleteById(id);

		return "Success";
	}

	@GetMapping("/getTheaterById")
	public TheaterEntity getTheaterById(@RequestParam(value = "id", required = true) long id) {
		log.info("Inside TheaterController: getTheaterById()");
		Optional<TheaterEntity> theaterEntity = repository.findById(id);
		log.info("TheaterController: getTheaterById() called successfully");
		return theaterEntity.get();
	}

}
