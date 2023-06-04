package com.devdahcoder.user.service;

import com.devdahcoder.user.contract.UserDetailsManagerContract;
import com.devdahcoder.user.model.UserCreateModel;
import com.devdahcoder.user.model.UserResponseModel;
import com.devdahcoder.user.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsManagerContract {

	private final Logger logger = LoggerFactory.getLogger(UserService.class);

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

		this.userRepository = userRepository;

		this.passwordEncoder = passwordEncoder;

	}

	@Override
	public List<UserResponseModel> findAllUsers() {

		logger.info("Service: finding all users");

		return userRepository.findAllUsers();

	}

	public UserResponseModel findUserById(long id) {

		logger.info("Service: finding user by id");

		return userRepository.findUserById(id);

	}

	@Override
	public UserResponseModel findUserByUsername(String username) {

		logger.info("Service: finding user by username");

		return userRepository.findUserByUsername(username);

	}

	@Override
	@Transactional
	public String createUser(@NotNull UserCreateModel userCreateModel) {

		logger.info("Service: creating a new user");

		userCreateModel.setPassword(passwordEncoder.encode(userCreateModel.getPassword()));

		userRepository.userExists(userCreateModel.getUsername());

		return userRepository.createUser(userCreateModel);

	}

	public void updateUser(UserDetails user) {

	}

	public void deleteUser(String username) {

	}

	public void changePassword(String oldPassword, String newPassword) {

	}

	@Override
	public boolean userExists(String username) {

		return userRepository.userExists(username);

	}

}
