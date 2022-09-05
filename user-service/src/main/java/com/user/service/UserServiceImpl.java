package com.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.UserEntity;
import com.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	 private UserRepository userRepository;

	@Override
	public UserEntity saveUser(UserEntity user) {
	
		return userRepository.save(user);
	}

	@Override
	public List<UserEntity> userList() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Optional<UserEntity> getUserById(long id) {
		// TODO Auto-generated method stub
		Optional<UserEntity> UserEntity = userRepository.findById(id);
		return UserEntity;
	}

	@Override
	public String deleteUser(long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
		return "Success";
	}

	@Override
	public UserEntity updateUser(UserEntity user) {
		
		UserEntity saveAndFlush = userRepository.save(user);
		return saveAndFlush;
	}

   
}
