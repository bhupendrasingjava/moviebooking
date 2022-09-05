package com.user.service;

import java.util.List;
import java.util.Optional;

import com.user.entity.UserEntity;

public interface UserService {

	UserEntity saveUser(UserEntity user);

	List<UserEntity> userList();

	Optional<UserEntity> getUserById(long id);

	String deleteUser(long id);

	UserEntity updateUser(UserEntity user);
}
