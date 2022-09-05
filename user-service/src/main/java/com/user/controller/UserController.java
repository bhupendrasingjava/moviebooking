package com.user.controller;

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

import com.user.entity.UserEntity;
import com.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/saveUser")
	public UserEntity saveUser(@RequestBody UserEntity user) {
		UserEntity saveUser = userService.saveUser(user);
		return saveUser;

	}

	@GetMapping("/getUser")
	public List<UserEntity> userList() {
		List<UserEntity> findAll = userService.userList();
		return findAll;
	}

	@PutMapping("/updateUser")
	public UserEntity updateUser(@RequestBody UserEntity user) {
		UserEntity updatedUser = userService.updateUser(user);
		return updatedUser;
	}

	@DeleteMapping("/deleteUser")
	public String deleteUser(@RequestParam(value = "id", required = true) long id) {

		return userService.deleteUser(id);

	}

	@GetMapping("/getUserById")
	public UserEntity getUserById(@RequestParam(value = "id", required = true) long id) {
		log.info("Inside UserController: getUserById()");
		Optional<UserEntity> userEntity = userService.getUserById(id);
		log.debug("UserController: getUserById() calles successfully");
		return userEntity.get();
	}


}
